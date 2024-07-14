package com.fiap.ms_parquimetro_cadastro.exception.carro;

import lombok.Getter;

@Getter
public class CarroNotFoundException extends RuntimeException {
    private static final String MESSAGE = "O carro com o id %s não existe.";
    private final String errorCode ="Carro Not Found";
    private final String placa;

    public CarroNotFoundException(String placa) {
        super(String.format(MESSAGE, placa));
        this.placa = placa;
    }
}
