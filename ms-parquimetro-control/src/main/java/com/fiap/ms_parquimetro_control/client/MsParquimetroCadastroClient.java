package com.fiap.ms_parquimetro_control.client;

import com.fiap.ms_parquimetro_control.repository.dto.CarroDTO;
import com.fiap.ms_parquimetro_control.repository.dto.ClienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "${api_host_parquimetro_cadastro}",name = "msParquimetroCadastroClient")
public interface MsParquimetroCadastroClient {

    @GetMapping(value = "/carro/placa/{placa}", produces = "*/*")
    CarroDTO getCarroByPlaca(@PathVariable String placa);

    @GetMapping(value = "/cliente/{id}", produces = "*/*")
    ClienteDTO getClienteById(@PathVariable String id);

}
