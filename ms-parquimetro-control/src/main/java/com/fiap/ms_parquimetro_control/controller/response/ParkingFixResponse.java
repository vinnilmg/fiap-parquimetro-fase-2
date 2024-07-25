package com.fiap.ms_parquimetro_control.controller.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ParkingFixResponse {
    private String placa;
    private LocalDateTime dataHoraEntrada;
    private Integer tempoFixo;
    private Double valorCalculado;
}
