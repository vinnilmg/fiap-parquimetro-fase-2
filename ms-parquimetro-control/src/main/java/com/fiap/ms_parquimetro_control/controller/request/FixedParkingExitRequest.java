package com.fiap.ms_parquimetro_control.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class FixedParkingExitRequest {
    @Size(min = 7, max = 7, message = "Placa inválida.")
    @NotBlank
    private String placa;
}
