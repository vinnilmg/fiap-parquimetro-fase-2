package com.fiap.ms_parquimetro_cadastro.controller.mapper;

import com.fiap.ms_parquimetro_cadastro.controller.response.CarroResponse;
import com.fiap.ms_parquimetro_cadastro.controller.resquest.CarroRequest;
import com.fiap.ms_parquimetro_cadastro.repository.entity.Carro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CarroMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "clienteId", target = "cliente.id")
    Carro CarroRequestToEntity(CarroRequest request);

    @Mapping(source = "cliente.id", target = "clienteId")
    CarroResponse CarroEntityToResponse(Carro carro);
}
