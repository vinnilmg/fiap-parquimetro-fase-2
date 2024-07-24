package com.fiap.ms_parquimetro_control.controller.response.mapper;

import com.fiap.ms_parquimetro_control.controller.response.FixedParkingExitResponse;
import com.fiap.ms_parquimetro_control.repository.db.entity.Estacionamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FixedParkingExitResponseMapper {
    FixedParkingExitResponseMapper INSTANCE = Mappers.getMapper(FixedParkingExitResponseMapper.class);
    @Mapping(target = "placa", source = "placa")
    @Mapping(target = "dataHoraSaida", expression = "java(java.time.LocalDateTime.now())")
    FixedParkingExitResponse toFixedParkingExitResponse(Estacionamento estacionamento);
}
