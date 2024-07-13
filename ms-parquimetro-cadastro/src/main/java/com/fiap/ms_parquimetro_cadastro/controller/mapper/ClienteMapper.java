package com.fiap.ms_parquimetro_cadastro.controller.mapper;

import com.fiap.ms_parquimetro_cadastro.controller.response.ClienteResponse;
import com.fiap.ms_parquimetro_cadastro.controller.resquest.ClienteRequest;
import com.fiap.ms_parquimetro_cadastro.repository.entity.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mappings({@Mapping(source = "clienteId", target = "cliente.id")})
    Cliente ClienteRequestToEntity(ClienteRequest request);

    @Mappings({@Mapping(source = "cliente.id", target = "clienteId")})
    ClienteRequest ClienteEntityToRequest(Cliente entity);

    @Mappings({@Mapping(source = "cliente.id", target = "clienteId")})
    ClienteResponse ClienteEntityToResponse(Cliente cliente);

    @Mappings({@Mapping(source = "clienteId", target = "cliente.id")})
    Cliente ClienteResponseToEntity(ClienteResponse response);
}
