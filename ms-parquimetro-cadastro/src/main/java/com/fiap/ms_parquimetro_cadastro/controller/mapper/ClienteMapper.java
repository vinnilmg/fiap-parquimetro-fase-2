package com.fiap.ms_parquimetro_cadastro.controller.mapper;

import com.fiap.ms_parquimetro_cadastro.controller.response.ClienteResponse;
import com.fiap.ms_parquimetro_cadastro.controller.resquest.ClienteRequest;
import com.fiap.ms_parquimetro_cadastro.repository.entity.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    Cliente ClienteRequestToEntity(ClienteRequest request);

    ClienteRequest ClienteEntityToRequest(Cliente entity);

    ClienteResponse ClienteEntityToResponse(Cliente cliente);

    Cliente ClienteResponseToEntity(ClienteResponse response);
}
