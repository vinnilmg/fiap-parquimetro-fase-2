package com.fiap.ms_parquimetro_cadastro.exception.cliente;

import com.fiap.ms_parquimetro_cadastro.exception.generic.CustomNotFoundException;
import lombok.Getter;

@Getter
public class ClienteNotFoundException extends CustomNotFoundException {
    private static final String MESSAGE = "O cliente com o id %s não existe.";

    public ClienteNotFoundException(String id) {
        super("",String.format(MESSAGE, id));
    }
}
