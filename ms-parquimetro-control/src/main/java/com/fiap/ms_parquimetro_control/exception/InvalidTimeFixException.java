package com.fiap.ms_parquimetro_control.exception;

public class InvalidTimeFixException extends RuntimeException {
    private static final String MESSAGE = "O tempo fixo deve ser maior do que 1 hora.";

    public InvalidTimeFixException() {
        super(MESSAGE);
    }
}