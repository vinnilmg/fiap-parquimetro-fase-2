package com.fiap.ms_parquimetro_cadastro.controller.resquest;

import com.fiap.ms_parquimetro_cadastro.repository.enums.FormaPagamentoPreferidaEnum;
import lombok.Data;

@Data
public class ClienteUpdateRequest {
    private String nome;

    private String cnh;

    private String telefone;

    private String email;

    private FormaPagamentoPreferidaEnum formaPagamentoPreferida;
}
