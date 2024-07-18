package com.fiap.ms_parquimetro_control.repository.mapper;


import com.fiap.ms_parquimetro_control.controller.request.ParkingFixRequest;
import com.fiap.ms_parquimetro_control.repository.entity.Estacionamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;

@Mapper(
        componentModel = "spring",
        imports = {LocalDateTime.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)

public interface EstacionamentoFixMapper {
    @Mapping(target = "placa", source = "placa")
    @Mapping(target = "observacoes", source = "observacoes")
    @Mapping(target = "status", constant = "INICIADO")
    @Mapping(target = "tipo", constant = "FIXO")
    @Mapping(target = "tempoFixo", source = "tempoFixo")
    @Mapping(target = "valorCalculado", constant = "20")
    @Mapping(target = "dataHoraEntrada", expression = "java(LocalDateTime.now())")
    Estacionamento toEstacionamentoFix(ParkingFixRequest request);
}
