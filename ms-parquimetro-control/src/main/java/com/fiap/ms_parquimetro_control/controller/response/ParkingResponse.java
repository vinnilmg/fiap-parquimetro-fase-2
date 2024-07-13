package com.fiap.ms_parquimetro_control.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParkingResponse {
    private String id;
    private String placa;
    private String dataHoraEntrada;
    @JsonInclude(NON_NULL)
    private String dataHoraSaida;
    private String tipoEstacionamento;
    private String status;
    @JsonInclude(NON_NULL)
    private Integer tempoFixo;
    @JsonInclude(NON_NULL)
    private String total;
    private String observacoes;
}
