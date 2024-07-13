package com.fiap.ms_parquimetro_control.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class DefaultRequest {
    @NotBlank(message = "Placa do carro deve ser preenchida.")
    @Size(min = 7, max = 7, message = "Placas contém 7 caracteres.")
    private String placa;
}
