package com.fiap.ms_parquimetro_control.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FinalizacaoRequest extends DefaultRequest {
    @NotBlank
    private String tipoPagamento;
}
