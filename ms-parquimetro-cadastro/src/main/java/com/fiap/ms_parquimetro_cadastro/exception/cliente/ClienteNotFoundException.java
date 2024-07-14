package com.fiap.ms_parquimetro_cadastro.exception.cliente;

import lombok.Getter;

@Getter
public class ClienteNotFoundException extends RuntimeException {
    private static final String MESSAGE = "O cliente com o id %s não existe.";
    private final String errorCode = "Cliente Not Found";

    private final String id;

    public ClienteNotFoundException(String id) {
        super(String.format(MESSAGE, id));
        this.id = id;
    }
}
