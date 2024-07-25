package com.fiap.ms_parquimetro_control.repository.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CarroDTO {
    private String marca;
    private String modelo;
    private String cor;
    private String placa;
    private String observacoes;
    private String clienteId;
}
