package com.fiap.ms_parquimetro_cadastro.exception.carro;

import com.fiap.ms_parquimetro_cadastro.exception.generic.CustomNotFoundException;
import lombok.Getter;

@Getter
public class TamanhoDaPlacaException extends CustomNotFoundException {
    private static final String MESSAGE = "O tamanho da placa %s deve ter 7 caracteres.";

    public TamanhoDaPlacaException(String placa) {
        super("",String.format(MESSAGE, placa));
    }
}
