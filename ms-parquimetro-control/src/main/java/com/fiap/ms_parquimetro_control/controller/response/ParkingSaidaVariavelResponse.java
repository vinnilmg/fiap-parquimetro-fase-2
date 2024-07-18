package com.fiap.ms_parquimetro_control.controller.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ParkingSaidaVariavelResponse {
    private String placa;
    private LocalDateTime dataHoraEntrada;
    private LocalDateTime dataHoraSaida;
    private Double valorCalculado;
}
