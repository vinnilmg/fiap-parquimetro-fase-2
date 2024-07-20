package com.fiap.ms_parquimetro_control.repository.mapper;

import com.fiap.ms_parquimetro_control.constants.ParquimetroConstants;
import com.fiap.ms_parquimetro_control.controller.request.ParkingFixRequest;
import com.fiap.ms_parquimetro_control.controller.request.ParkingPerHourRequest;
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
public interface EstacionamentoMapper {

    @Mapping(target = "placa", source = "placa")
    @Mapping(target = "observacoes", source = "observacoes")
    @Mapping(target = "status", constant = "INICIADO")
    @Mapping(target = "tipo", constant = "HORA")
    @Mapping(target = "dataHoraEntrada", expression = "java(LocalDateTime.now())")
    Estacionamento toEstacionamento(ParkingPerHourRequest request);

    @Mapping(target = "placa", source = "placa")
    @Mapping(target = "tempoFixo", source = "tempoFixo")
    @Mapping(target = "observacoes", source = "observacoes")
    @Mapping(target = "status", constant = "INICIADO")
    @Mapping(target = "tipo", constant = "FIXO")
    @Mapping(target = "dataHoraEntrada", expression = "java(LocalDateTime.now())")
    // TODO: Calcular o valor com o tempo FIXO
    Estacionamento toEstacionamento(ParkingFixRequest request);

    @Mapping(target = "placa", source = "placa")
    @Mapping(target = "valorCalculado", expression = "java(getValor(request.getDataHoraEntrada()))")
    @Mapping(target = "dataHoraEntrada", source = "dataHoraEntrada")
    @Mapping(target = "dataHoraSaida", expression = "java(LocalDateTime.now())")
    Estacionamento toEstacionamentoSaidaVariavel(Estacionamento request);

    default Double getValor(LocalDateTime time1) {
        var time2 = LocalDateTime.now();
        var totalTime = (time2.getHour() - time1.getHour()) + 1;
        return ParquimetroConstants.VALOR_HORA_TIPO_VARIAVEL * totalTime;
    }
}
