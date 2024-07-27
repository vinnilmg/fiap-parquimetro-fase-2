package com.fiap.ms_parquimetro_control.repository.db.entity;

import com.fiap.ms_parquimetro_control.repository.db.enums.StatusEnum;
import com.fiap.ms_parquimetro_control.repository.db.enums.TipoEstacionamentoEnum;
import com.fiap.ms_parquimetro_control.repository.db.enums.TipoPagamentoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "estacionamento")
public class Estacionamento implements Serializable {
    @Serial
    private static final long serialVersionUID = 1;

    @Id
    private String id;
    @NotBlank
    @Size(min = 7, max = 7)
    private String placa;
    @NotNull
    private LocalDateTime dataHoraEntrada;
    private LocalDateTime dataHoraSaida;
    private Integer tempoFixo;
    private BigDecimal valorCalculado;
    @NotNull
    private TipoEstacionamentoEnum tipo;
    @NotNull
    private StatusEnum status;
    private TipoPagamentoEnum pagamento;
    private String observacoes;
    private Integer horasEstacionadas;
    private Integer horasExcedentes;

    public void finalizaEstacionamento(final TipoPagamentoEnum tipoPagamento) {
        this.setPagamento(tipoPagamento);
        this.setStatus(StatusEnum.FINALIZADO);
    }
}
