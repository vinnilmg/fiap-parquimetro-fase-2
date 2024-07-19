package com.fiap.ms_parquimetro_cadastro.exception.generic;

public class CustomNotFoundException extends RuntimeException {
    private final String errorCode;

    public CustomNotFoundException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}