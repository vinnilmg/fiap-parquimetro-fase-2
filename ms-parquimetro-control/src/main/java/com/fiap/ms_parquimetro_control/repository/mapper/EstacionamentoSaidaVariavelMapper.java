package com.fiap.ms_parquimetro_control.repository.mapper;

import com.fiap.ms_parquimetro_control.controller.request.ParkingSaidaVariavelRequest;
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
public interface EstacionamentoSaidaVariavelMapper {
    @Mapping(target = "placa", source = "placa")
    @Mapping(target = "valorCalculado", expression = "java(getValor(request.getDataHoraEntrada()))")
    @Mapping(target = "dataHoraEntrada", source = "dataHoraEntrada")
    @Mapping(target = "dataHoraSaida", expression = "java(LocalDateTime.now())")
    Estacionamento toEstacionamentoSaidaVariavel(Estacionamento request);

    default Double getValor(LocalDateTime time1) {
        var time2 = LocalDateTime.now();
        var totalTime = time2.getHour() - time1.getHour();

        return 5.0 * totalTime;
    }
}
