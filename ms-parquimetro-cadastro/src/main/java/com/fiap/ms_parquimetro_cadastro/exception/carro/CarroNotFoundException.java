package com.fiap.ms_parquimetro_cadastro.exception.carro;

import com.fiap.ms_parquimetro_cadastro.exception.generic.CustomNotFoundException;
import lombok.Getter;

@Getter
public class CarroNotFoundException extends CustomNotFoundException {

    private static final String MESSAGE = "O carro com o id %s não existe.";
    private static final String errorCode ="Carro Not Found";

    public CarroNotFoundException(String placa) {
        super(errorCode,String.format(MESSAGE, placa));
    }
}
