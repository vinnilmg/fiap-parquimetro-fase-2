package com.fiap.ms_parquimetro_control.repository.db.enums;

import java.util.Arrays;

public enum FormaPagamentoPreferidaEnum {
    PIX,
    CREDITO,
    DEBITO,
    DINHEIRO;

    public static FormaPagamentoPreferidaEnum toEnum(final String value) {
        return Arrays.stream(values())
                .filter(fpp -> fpp.name().equals(value.toUpperCase()))
                .findFirst()
                .orElse(null);
    }
}
