package com.fiap.ms_parquimetro_control.controller.response.mapper;

import com.fiap.ms_parquimetro_control.controller.response.ParkingSaidaVariavelResponse;
import com.fiap.ms_parquimetro_control.repository.db.entity.Estacionamento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParkingSaidaVariavelResponseMapper {
    ParkingSaidaVariavelResponse toParkingSaidaVariavelResponse(Estacionamento estacionamento);
}
