package com.fiap.ms_parquimetro_control.controller.response;

import java.time.LocalDateTime;

public class FixedParkingExitResponse {
    private String placa;
    private LocalDateTime dataHoraEntrada;
    private LocalDateTime dataHoraSaida;
    private Integer tempoFixo;
}
