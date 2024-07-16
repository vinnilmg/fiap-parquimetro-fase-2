package com.fiap.ms_parquimetro_control.repository.entity;

import com.fiap.ms_parquimetro_control.repository.enums.StatusEnum;
import com.fiap.ms_parquimetro_control.repository.enums.TipoEstacionamentoEnum;
import com.fiap.ms_parquimetro_control.repository.enums.TipoPagamentoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "estacionamento")
public class Estacionamento {
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

    public void finalizaEstacionamento(final TipoPagamentoEnum tipoPagamento) {
        this.setPagamento(tipoPagamento);
        this.setStatus(StatusEnum.FINALIZADO);
    }
}
