package com.fiap.ms_parquimetro_control.exception;

public class ParkingNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Estacionamento não localizado para a placa informada.";

    public ParkingNotFoundException() {
        super(MESSAGE);
    }
}
