package com.fiap.ms_parquimetro_cadastro.utils;

import lombok.experimental.UtilityClass;
@UtilityClass
public class BooleanFunctions {

    public static void validateTrue(boolean condition, RuntimeException exception) {
        if (condition) {
            throw exception;
        }
    }
    public static void validateFalse(boolean condition, RuntimeException exception) {
        if (!condition) {
            throw exception;
        }
    }
}
