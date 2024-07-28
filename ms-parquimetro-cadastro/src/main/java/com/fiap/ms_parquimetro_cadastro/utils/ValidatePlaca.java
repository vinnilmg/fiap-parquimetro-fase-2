package com.fiap.ms_parquimetro_cadastro.utils;

import com.fiap.ms_parquimetro_cadastro.exception.carro.TamanhoDaPlacaException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidatePlaca {
    public static void validatePlacaLenght(String placa){
       if(placa !=null && placa.length()!=7){
           throw new TamanhoDaPlacaException(placa);
       }

    }
}
