package com.fiap.ms_parquimetro_cadastro.config.exception;

import com.fiap.ms_parquimetro_cadastro.exception.carro.CarroNotFoundException;
import com.fiap.ms_parquimetro_cadastro.exception.carro.CarroPlacaNotFoundException;
import com.fiap.ms_parquimetro_cadastro.exception.carro.PlacaJaUtilizadaException;
import com.fiap.ms_parquimetro_cadastro.exception.cliente.ClienteCnhNotFoundException;
import com.fiap.ms_parquimetro_cadastro.exception.cliente.ClienteNotFoundException;
import com.fiap.ms_parquimetro_cadastro.exception.cliente.CnhJaUtilizadaException;
import com.fiap.ms_parquimetro_cadastro.exception.cliente.UUIDClienteInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({CarroNotFoundException.class, ClienteNotFoundException.class, CarroPlacaNotFoundException.class, ClienteCnhNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<CustomErrorResponse> handleNotFoundException(Exception ex) {
        CustomErrorResponse response = new CustomErrorResponse(HttpStatus.NOT_FOUND.getReasonPhrase(), ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            PlacaJaUtilizadaException.class,
            UUIDClienteInvalidException.class,
            CnhJaUtilizadaException.class,
            MethodArgumentNotValidException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomErrorResponse> handleBadRequestException(Exception e) {
        String message = e.getMessage();
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException manve = (MethodArgumentNotValidException) e;
            Map<String, String> errors = new HashMap<>();
            manve.getBindingResult().getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage())
            );
            message = errors.toString();
        }
        CustomErrorResponse response = new CustomErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), message);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleInternalServerError(
            final Exception e, final WebRequest request
    ) {
        final var response = new CustomErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage());
        return ResponseEntity.internalServerError().body(response);
    }
}
