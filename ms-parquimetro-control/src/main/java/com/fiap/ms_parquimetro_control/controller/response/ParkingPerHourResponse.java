package com.fiap.ms_parquimetro_control.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParkingPerHourResponse {
    private String id;
    private String placa;
    private String dataHoraEntrada;
    private String tipo;
    private String status;
    private String observacoes;
}
