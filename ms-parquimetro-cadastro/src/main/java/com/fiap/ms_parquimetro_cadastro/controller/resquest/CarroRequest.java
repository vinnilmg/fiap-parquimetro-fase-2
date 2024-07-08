package com.fiap.ms_parquimetro_cadastro.controller.resquest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarroRequest {
    @NotBlank(message = "Marca é obrigatória")
    @Size(max = 50, message = "Marca deve ter no máximo 50 caracteres")
    private String marca;

    @NotBlank(message = "Modelo é obrigatório")
    @Size(max = 50, message = "Modelo deve ter no máximo 50 caracteres")
    private String modelo;

    @NotBlank(message = "Cor é obrigatória")
    @Size(max = 50, message = "Cor deve ter no máximo 50 caracteres")
    private String cor;

    @NotBlank(message = "Placa é obrigatória")
    @Size(min = 7, max = 7, message = "Placa deve ter exatamente 7 caracteres")
    private String placa;

    @Size(max = 500, message = "Observações devem ter no máximo 500 caracteres")
    private String observacoes;

    @NotNull
    private UUID clienteId;
}
