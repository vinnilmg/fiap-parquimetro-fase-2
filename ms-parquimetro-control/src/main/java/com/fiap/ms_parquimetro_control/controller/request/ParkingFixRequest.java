package com.fiap.ms_parquimetro_control.controller.request;

import com.fiap.ms_parquimetro_control.repository.db.enums.TipoPagamentoEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ParkingFixRequest {
    @Size(min = 7, max = 7, message = "Placa inválida.")
    private String placa;
    @NotNull
    private Integer tempoFixo;
    private String observacoes;
    private String pagamento;
}


