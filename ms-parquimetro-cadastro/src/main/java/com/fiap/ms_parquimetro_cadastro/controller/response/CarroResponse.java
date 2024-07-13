package com.fiap.ms_parquimetro_cadastro.controller.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarroResponse {
    private String marca;
    private String modelo;
    private String cor;
    private String placa;
    private String observacoes;
    private UUID clienteId;
}
