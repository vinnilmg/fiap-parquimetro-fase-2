package com.fiap.ms_parquimetro_control.controller.response;

import com.fiap.ms_parquimetro_control.repository.db.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FixedParkingExitResponse {
    private String placa;
    private LocalDateTime dataHoraEntrada;
    private LocalDateTime dataHoraSaida;
    private Integer tempoFixo;
    private Integer horasEstacionadas;
    private Double horasExcedentes;
    private BigDecimal valorCalculado;
    private StatusEnum status;
}
