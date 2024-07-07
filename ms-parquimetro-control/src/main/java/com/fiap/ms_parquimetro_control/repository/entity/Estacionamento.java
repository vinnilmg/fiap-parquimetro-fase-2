package com.fiap.ms_parquimetro_control.repository.entity;

import com.fiap.ms_parquimetro_control.repository.enums.StatusEnum;
import com.fiap.ms_parquimetro_control.repository.enums.TipoEstacionamentoEnum;
import com.fiap.ms_parquimetro_control.repository.enums.TipoPagamentoEnum;
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
    private String placa;
    private LocalDateTime dataHoraEntrada;
    private LocalDateTime dataHoraSaida;
    private Integer tempoFixo;
    private Double valorCalculado;
    private TipoEstacionamentoEnum tipo;
    private StatusEnum status;
    private TipoPagamentoEnum pagamento;
    private String observacoes;
}
