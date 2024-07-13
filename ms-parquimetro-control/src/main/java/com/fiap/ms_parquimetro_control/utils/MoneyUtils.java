package com.fiap.ms_parquimetro_control.utils;

import java.math.BigDecimal;

import static java.util.Objects.isNull;

public class MoneyUtils {
    private static final String REAL = "R$";

    private MoneyUtils() {}

    public static String toMoney(final BigDecimal value) {
        if (isNull(value)) return null;
        return String.format("%s %.2f", REAL, value);
    }
}
