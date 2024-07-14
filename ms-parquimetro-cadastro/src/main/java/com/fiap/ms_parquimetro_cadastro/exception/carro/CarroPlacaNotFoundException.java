package com.fiap.ms_parquimetro_cadastro.exception.carro;

import lombok.Getter;

@Getter
public class CarroPlacaNotFoundException extends RuntimeException {

    private static final String MESSAGE = "O carro com a placa %s não existe.";
    private final String errorCode ="Carro Placa Not Found";
    private final String placa;

    public CarroPlacaNotFoundException(String placa) {
        super(String.format(MESSAGE, placa));
        this.placa = placa;
    }
}
