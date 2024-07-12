package com.fiap.ms_parquimetro_cadastro.repository.entity;

import com.fiap.ms_parquimetro_cadastro.repository.enums.FormaPagamentoPreferidaEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;

    @Column(unique = true)
    private String cnh;

    private String telefone;

    @Email
    private String email;

    @Enumerated(EnumType.STRING)
    private FormaPagamentoPreferidaEnum formaPagamentoPreferida;


}
