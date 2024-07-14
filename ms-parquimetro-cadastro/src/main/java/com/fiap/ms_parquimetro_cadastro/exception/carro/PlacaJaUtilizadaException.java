package com.fiap.ms_parquimetro_cadastro.exception.carro;

import lombok.Getter;

@Getter
public class PlacaJaUtilizadaException extends RuntimeException{
    private static final String MESSAGE = "O carro com a placa %s já existe.";
    private final String errorCode ="Placa Já Utilizada";
    private final String placa;

    public PlacaJaUtilizadaException(String placa) {
        super(String.format(MESSAGE, placa));
        this.placa = placa;
    }
}
