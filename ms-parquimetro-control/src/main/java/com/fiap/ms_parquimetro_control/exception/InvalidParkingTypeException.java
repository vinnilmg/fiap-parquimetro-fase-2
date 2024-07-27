package com.fiap.ms_parquimetro_control.exception;

public class InvalidParkingTypeException extends RuntimeException {
    private static final String MESSAGE = "Tipo de estacionamento inválido para executar essa ação.";

    public InvalidParkingTypeException() {
        super(MESSAGE);
    }
}
