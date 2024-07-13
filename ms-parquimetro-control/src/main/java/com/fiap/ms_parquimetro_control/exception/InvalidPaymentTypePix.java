package com.fiap.ms_parquimetro_control.exception;

public class InvalidPaymentTypePix extends RuntimeException {
    private static final String MESSAGE = "Pix só está disponível para períodos de estacionamento FIXO";

    public InvalidPaymentTypePix() {
        super(MESSAGE);
    }
}
