package com.fiap.ms_parquimetro_control.repository.db.mapper;

import com.fiap.ms_parquimetro_control.controller.request.ParkingPerHourRequest;
import com.fiap.ms_parquimetro_control.repository.db.entity.Estacionamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;

@Mapper(
        componentModel = "spring",
        imports = {LocalDateTime.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface EstacionamentoMapper {

    @Mapping(target = "placa", source = "placa")
    @Mapping(target = "observacoes", source = "observacoes")
    @Mapping(target = "status", constant = "INICIADO")
    @Mapping(target = "tipo", constant = "HORA")
    @Mapping(target = "dataHoraEntrada", expression = "java(LocalDateTime.now())")
    Estacionamento toEstacionamento(ParkingPerHourRequest request);
}
