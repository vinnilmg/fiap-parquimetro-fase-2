package com.fiap.ms_parquimetro_control.exception;

public class InvalidPaymentTypeException extends RuntimeException {
    private static final String MESSAGE = "Informe um tipo de pagamento aceito por nós.";

    public InvalidPaymentTypeException() {
        super(MESSAGE);
    }
}
