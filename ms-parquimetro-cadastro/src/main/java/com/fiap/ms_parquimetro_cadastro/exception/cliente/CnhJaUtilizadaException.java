package com.fiap.ms_parquimetro_cadastro.exception.cliente;

import com.fiap.ms_parquimetro_cadastro.exception.generic.CustomNotFoundException;
import lombok.Getter;

@Getter
public class CnhJaUtilizadaException extends CustomNotFoundException {

    private static final String MESSAGE = "O cliente com a CNH %s já está cadastrado.";
    private static final String errorCode = "CNH já utilizada.";

    public CnhJaUtilizadaException(String cnh) {
        super(errorCode, String.format(MESSAGE, cnh));
    }


}
