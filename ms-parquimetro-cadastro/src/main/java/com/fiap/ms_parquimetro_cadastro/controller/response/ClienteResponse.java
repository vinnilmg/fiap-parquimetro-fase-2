package com.fiap.ms_parquimetro_cadastro.controller.response;

import com.fiap.ms_parquimetro_cadastro.repository.enums.FormaPagamentoPreferidaEnum;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteResponse {

    private String nome;
    private String cnh;
    private String telefone;
    private String email;
    private FormaPagamentoPreferidaEnum formaPagamentoPreferida;
}
