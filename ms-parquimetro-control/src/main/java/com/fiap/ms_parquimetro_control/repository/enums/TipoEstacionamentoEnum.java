package com.fiap.ms_parquimetro_control.repository.enums;

import lombok.Getter;

import java.math.BigDecimal;

import static com.fiap.ms_parquimetro_control.constants.ParquimetroConstants.VALOR_HORA_TIPO_FIXO;
import static com.fiap.ms_parquimetro_control.constants.ParquimetroConstants.VALOR_HORA_TIPO_VARIAVEL;

@Getter
public enum TipoEstacionamentoEnum {
    FIXO(VALOR_HORA_TIPO_FIXO),
    HORA(VALOR_HORA_TIPO_VARIAVEL);

    TipoEstacionamentoEnum(BigDecimal taxa) {
        this.taxa = taxa;
    }

    private final BigDecimal taxa;
}
