package com.fiap.ms_parquimetro_control.exception;

public class InvalidTimeFix extends RuntimeException {
    private static final String MESSAGE = "O tempo fixo deve ser maior do que 1 hora.";

    public InvalidTimeFix() {
        super(MESSAGE);
    }
}