package com.fiap.ms_parquimetro_control.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "estacionamento")
public class Estacionamento {

    @Id
    private String id;
    private LocalDateTime dataHoraEntrada;
    private LocalDateTime dataHoraSaida;
    private int tempoFixo;
    private double valorCalculado;
}
