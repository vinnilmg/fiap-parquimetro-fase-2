package com.fiap.ms_parquimetro_control.config.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.ms_parquimetro_control.exception.CarNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class CustomErrorDecoder implements ErrorDecoder {
    private static final Log log = LogFactory.getLog(CustomErrorDecoder.class);

    @Override
    public Exception decode(String method, Response response) {
        log.info(String.format("Error decoding{%s %s}",method, response));
        ObjectMapper mapper = new ObjectMapper();
        if (method.contains("getCarroByPlaca")) {
            try{
                Map<String, String> mapResponse = mapper.readValue(response.body().asInputStream(),Map.class);
                return new CarNotFoundException(mapResponse.get("errorCode"));
            }catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return new RuntimeException("ExceptionNotMapped");
    }
}
