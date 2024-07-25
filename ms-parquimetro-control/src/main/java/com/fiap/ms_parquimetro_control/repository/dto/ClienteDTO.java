package com.fiap.ms_parquimetro_control.repository.dto;

import com.fiap.ms_parquimetro_control.repository.db.enums.FormaPagamentoPreferidaEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {

    private String  formaPagamentoPreferida;
}
