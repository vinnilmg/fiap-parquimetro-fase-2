package com.fiap.ms_parquimetro_cadastro.utils;


import com.fiap.ms_parquimetro_cadastro.exception.cliente.UUIDClienteInvalidException;
import lombok.experimental.UtilityClass;

import static java.util.UUID.fromString;
@UtilityClass
public class ValidateUUID {

    public static void isValidUUID(String uuid) {
        try {
            fromString(uuid);
        } catch (IllegalArgumentException e) {
            throw new UUIDClienteInvalidException(uuid);
        }


    }
}
