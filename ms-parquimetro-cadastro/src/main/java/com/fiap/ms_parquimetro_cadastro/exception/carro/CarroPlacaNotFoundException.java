package com.fiap.ms_parquimetro_cadastro.exception.carro;

import com.fiap.ms_parquimetro_cadastro.exception.generic.CustomNotFoundException;
import lombok.Getter;

@Getter
public class CarroPlacaNotFoundException extends CustomNotFoundException {

    private static final String MESSAGE = "O carro com a placa %s não existe.";
    private static final String errorCode = "Carro Placa Not Found";

    public CarroPlacaNotFoundException(String placa) {
        super(String.format(MESSAGE, placa), errorCode);
    }
}
