package com.fiap.ms_parquimetro_control.controller.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ParkingPerHourResponse {
    private String id;
    private String placa;
    private LocalDateTime dataHoraEntrada;
    private String tipo;
    private String status;
    private String observacoes;
}
