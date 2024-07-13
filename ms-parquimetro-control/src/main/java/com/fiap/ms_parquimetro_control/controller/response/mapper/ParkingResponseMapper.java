package com.fiap.ms_parquimetro_control.controller.response.mapper;

import com.fiap.ms_parquimetro_control.controller.response.ParkingResponse;
import com.fiap.ms_parquimetro_control.repository.entity.Estacionamento;
import com.fiap.ms_parquimetro_control.utils.DateUtils;
import com.fiap.ms_parquimetro_control.utils.MoneyUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = {MoneyUtils.class})
public interface ParkingResponseMapper {

    @Mapping(target = "dataHoraEntrada", source = "dataHoraEntrada", qualifiedByName = "formatDate")
    @Mapping(target = "dataHoraSaida", source = "dataHoraSaida", qualifiedByName = "formatDate")
    @Mapping(target = "tipoEstacionamento", source = "tipo")
    @Mapping(target = "total", expression = "java(MoneyUtils.toMoney(estacionamento.getValorCalculado()))")
    ParkingResponse toParkingResponse(Estacionamento estacionamento);

    @Named("formatDate")
    default String formatDate(final LocalDateTime time) {
        return DateUtils.toDefaultPattern(time);
    }
}
