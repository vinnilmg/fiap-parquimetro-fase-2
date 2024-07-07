package com.fiap.ms_parquimetro_control.controller.request;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ParkingPerHourRequest {
    @Size(min = 7, max = 7, message = "Placa inválida.")
    private String placa;
    private String observacoes;
}
