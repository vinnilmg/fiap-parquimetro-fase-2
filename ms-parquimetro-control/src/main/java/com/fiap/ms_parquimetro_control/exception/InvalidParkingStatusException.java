package com.fiap.ms_parquimetro_control.exception;

public class InvalidParkingStatusException extends RuntimeException {
    private static final String MESSAGE = "Status atual do ticket é inválido para finalização.";

    public InvalidParkingStatusException() {
        super(MESSAGE);
    }
}
