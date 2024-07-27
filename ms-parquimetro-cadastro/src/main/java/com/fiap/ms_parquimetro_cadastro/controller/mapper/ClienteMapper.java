package com.fiap.ms_parquimetro_cadastro.controller.mapper;

import com.fiap.ms_parquimetro_cadastro.controller.response.ClienteResponse;
import com.fiap.ms_parquimetro_cadastro.controller.resquest.ClienteRequest;
import com.fiap.ms_parquimetro_cadastro.repository.entity.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    Cliente ClienteRequestToEntity(ClienteRequest request);

    ClienteResponse ClienteEntityToResponse(Cliente cliente);
}
