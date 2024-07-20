package com.fiap.ms_parquimetro_cadastro.exception.carro;

import com.fiap.ms_parquimetro_cadastro.exception.generic.CustomNotFoundException;
import lombok.Getter;

@Getter
public class PlacaJaUtilizadaException  extends CustomNotFoundException {
    private static final String MESSAGE = "O carro com a placa %s já existe.";
    private static final String errorCode ="Placa Já Utilizada";

    public PlacaJaUtilizadaException(String placa) {
        super(errorCode,String.format(MESSAGE, placa));
    }
}
