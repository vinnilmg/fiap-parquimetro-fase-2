package com.fiap.ms_parquimetro_control.controller.response.mapper;

import com.fiap.ms_parquimetro_control.controller.response.ParkingFixResponse;

import com.fiap.ms_parquimetro_control.repository.db.entity.Estacionamento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParkingFixResponseMapper {
    ParkingFixResponse toParkingFixResponse(Estacionamento estacionamento);
}