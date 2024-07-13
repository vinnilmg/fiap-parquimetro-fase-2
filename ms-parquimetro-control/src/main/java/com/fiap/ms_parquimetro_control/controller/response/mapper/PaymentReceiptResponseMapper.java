package com.fiap.ms_parquimetro_control.controller.response.mapper;

import com.fiap.ms_parquimetro_control.controller.response.PaymentReceiptResponse;
import com.fiap.ms_parquimetro_control.repository.entity.Estacionamento;
import com.fiap.ms_parquimetro_control.repository.enums.TipoEstacionamentoEnum;
import com.fiap.ms_parquimetro_control.utils.DateUtils;
import com.fiap.ms_parquimetro_control.utils.MoneyUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = {MoneyUtils.class})
public interface PaymentReceiptResponseMapper {

    @Mapping(target = "dataHoraInicio", source = "dataHoraEntrada", qualifiedByName = "formatDate")
    @Mapping(target = "dataHoraSaida", source = "dataHoraSaida", qualifiedByName = "formatDate")
    @Mapping(target = "valorTotalPago", expression = "java(MoneyUtils.toMoney(estacionamento.getValorCalculado()))")
    @Mapping(target = "tempoEstacionado", source = "estacionamento", qualifiedByName = "mapTempoEstacionado")
    @Mapping(target = "taxaPorHora", source = "tipo", qualifiedByName = "getTaxaPorHora")
    PaymentReceiptResponse toPaymentReceiptResponse(Estacionamento estacionamento);

    @Named("getTaxaPorHora")
    default String getTaxaPorHora(final TipoEstacionamentoEnum tipo) {
        return MoneyUtils.toMoney(tipo.getTaxa());
    }

    @Named("mapTempoEstacionado")
    default String mapTempoEstacionado(final Estacionamento estacionamento) {
        return DateUtils.calculaPeriodoEntreDatas(
                estacionamento.getDataHoraEntrada(),
                estacionamento.getDataHoraSaida());
    }

    @Named("formatDate")
    default String formatDate(final LocalDateTime time) {
        return DateUtils.toDefaultPattern(time);
    }
}
