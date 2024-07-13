package com.fiap.ms_parquimetro_control.controller.response;

import lombok.Data;

@Data
public class PaymentReceiptResponse {
    private String dataHoraInicio;
    private String dataHoraSaida;
    private String tempoEstacionado;
    private String taxaPorHora;
    private String valorTotalPago;
}
