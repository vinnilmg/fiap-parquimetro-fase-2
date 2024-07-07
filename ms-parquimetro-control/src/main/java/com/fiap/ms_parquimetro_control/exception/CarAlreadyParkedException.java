package com.fiap.ms_parquimetro_control.exception;

import lombok.Getter;

@Getter
public class CarAlreadyParkedException extends RuntimeException {
    private static final String MESSAGE = "O carro com a placa %s já está estacionado.";

    private final String placa;

    public CarAlreadyParkedException(String placa) {
        super(String.format(MESSAGE, placa));
        this.placa = placa;
    }
}
