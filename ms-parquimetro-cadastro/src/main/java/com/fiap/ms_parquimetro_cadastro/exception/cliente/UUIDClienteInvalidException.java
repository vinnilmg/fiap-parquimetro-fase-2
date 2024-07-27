package com.fiap.ms_parquimetro_cadastro.exception.cliente;

import com.fiap.ms_parquimetro_cadastro.exception.generic.CustomNotFoundException;

public class UUIDClienteInvalidException extends CustomNotFoundException {

    private static final String MESSAGE = "O id %s não é um UUID válido.";
    private static final String errorCode = "UUID invalid";


    public UUIDClienteInvalidException(String id) {
        super(String.format(MESSAGE, id), errorCode);
    }
}
