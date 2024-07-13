package com.fiap.ms_parquimetro_control.repository.entity;

import com.fiap.ms_parquimetro_control.exception.InvalidParkingStatusException;
import com.fiap.ms_parquimetro_control.exception.InvalidPaymentTypePix;
import com.fiap.ms_parquimetro_control.repository.enums.StatusEnum;
import com.fiap.ms_parquimetro_control.repository.enums.TipoEstacionamentoEnum;
import com.fiap.ms_parquimetro_control.repository.enums.TipoPagamentoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.fiap.ms_parquimetro_control.repository.enums.StatusEnum.PAGAMENTO_PENDENTE;
import static com.fiap.ms_parquimetro_control.repository.enums.TipoEstacionamentoEnum.FIXO;
import static com.fiap.ms_parquimetro_control.repository.enums.TipoPagamentoEnum.PIX;

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
        if (!this.getStatus().equals(PAGAMENTO_PENDENTE)) {
            throw new InvalidParkingStatusException();
        }

        if (tipoPagamento.equals(PIX) && !this.getTipo().equals(FIXO)) {
            throw new InvalidPaymentTypePix();
        }

        this.setPagamento(tipoPagamento);
        this.setStatus(StatusEnum.FINALIZADO);
    }
}
