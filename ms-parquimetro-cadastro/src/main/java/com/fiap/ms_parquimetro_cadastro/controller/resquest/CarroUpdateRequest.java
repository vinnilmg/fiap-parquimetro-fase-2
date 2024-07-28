package com.fiap.ms_parquimetro_cadastro.controller.resquest;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarroUpdateRequest {
    private String marca;

    private String modelo;

    private String cor;

    private String placa;

    private String observacoes;

    private String clienteId;
}
