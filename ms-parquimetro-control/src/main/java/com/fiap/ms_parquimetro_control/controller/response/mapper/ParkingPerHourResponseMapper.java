package com.fiap.ms_parquimetro_control.controller.response.mapper;

import com.fiap.ms_parquimetro_control.controller.response.ParkingPerHourResponse;
import com.fiap.ms_parquimetro_control.repository.entity.Estacionamento;
import com.fiap.ms_parquimetro_control.utils.DateUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = {DateUtils.class})
public interface ParkingPerHourResponseMapper {

    @Mapping(target = "dataHoraEntrada", expression = "java(DateUtils.toDefaultPattern(estacionamento.getDataHoraEntrada()))")
    ParkingPerHourResponse toParkingPerHourResponse(Estacionamento estacionamento);
}
