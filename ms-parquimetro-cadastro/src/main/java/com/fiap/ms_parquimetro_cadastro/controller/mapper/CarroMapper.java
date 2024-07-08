package com.fiap.ms_parquimetro_cadastro.controller.mapper;

import com.fiap.ms_parquimetro_cadastro.controller.response.CarroResponse;
import com.fiap.ms_parquimetro_cadastro.controller.resquest.CarroRequest;
import com.fiap.ms_parquimetro_cadastro.repository.entity.Carro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CarroMapper {

    @Mappings({
            @Mapping(source = "clienteId", target = "cliente.id")
    })
    Carro CarroRequestToEntity(CarroRequest request);

    @Mappings({
            @Mapping(source = "cliente.id", target = "clienteId")
    })
    CarroRequest CarroEntityToRequest(Carro entity);

    @Mappings({
            @Mapping(source = "cliente.id", target = "clienteId")
    })
    CarroResponse CarroEntityToResponse(Carro carro);

    @Mappings({
            @Mapping(source = "clienteId", target = "cliente.id")
    })
    Carro CarroResponseToEntity(CarroResponse response);
}
