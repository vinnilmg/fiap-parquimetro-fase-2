package com.fiap.ms_parquimetro_cadastro.controller.resquest;

import com.fiap.ms_parquimetro_cadastro.repository.enums.FormaPagamentoPreferidaEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteRequest {
    @NotBlank(message = "Nome é obrigatório.")
    @Size(max = 100, message = "Nome deve conter no máximo 100 caracteres.")
    private String nome;

    @NotBlank(message = "CNH é obrigatória.")
    @Size(max = 11, message = "CNH deve conter 11 caracteres.")
    private String cnh;

    @NotBlank(message = "Telefone é obrigatório.")
    @Size(max = 12, message = "Telefone deve conter 12 caracteres.")
    private String telefone;

    @NotBlank
    @Email(message = "O e-mail deve ser válido.")
    private String email;

    @NotNull
    private FormaPagamentoPreferidaEnum formaPagamentoPreferida;
}
