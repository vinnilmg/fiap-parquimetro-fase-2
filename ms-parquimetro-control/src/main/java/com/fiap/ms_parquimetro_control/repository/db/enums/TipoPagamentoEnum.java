package com.fiap.ms_parquimetro_control.repository.db.enums;

import com.fiap.ms_parquimetro_control.exception.InvalidPaymentType;

import java.util.stream.Stream;

public enum TipoPagamentoEnum {
    CREDITO,
    DEBITO,
    PIX;

    public static TipoPagamentoEnum toEnum(final String value) {
        return Stream.of(values())
                .filter(tipo -> tipo.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(InvalidPaymentType::new);
    }
}
