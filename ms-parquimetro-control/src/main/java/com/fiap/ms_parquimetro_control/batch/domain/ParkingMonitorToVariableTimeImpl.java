package com.fiap.ms_parquimetro_control.batch.domain;

import com.fiap.ms_parquimetro_control.repository.db.entity.Estacionamento;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

import static com.fiap.ms_parquimetro_control.utils.DateUtils.calculaHorasEntreDatas;
import static com.fiap.ms_parquimetro_control.utils.DateUtils.calculaMinutosEntreDatas;

@Slf4j
public class ParkingMonitorToVariableTimeImpl implements ParkingMonitorToVariableTime {
    private static final int MINUTES_TO_NOTIFY = 50;

    private final String placa;
    private final LocalDateTime now;
    private final LocalDateTime dataHoraEntrada;
    private final long minutosEstacionados;

    public ParkingMonitorToVariableTimeImpl(final Estacionamento parking) {
        this.now = LocalDateTime.now();
        this.placa = parking.getPlaca();
        this.dataHoraEntrada = parking.getDataHoraEntrada();
        final var horasEstacionadas = calculaHorasEntreDatas(dataHoraEntrada, now);
        this.minutosEstacionados = calculaMinutosEntreDatas(dataHoraEntrada, now, horasEstacionadas);
    }

    @Override
    public String placa() {
        return placa;
    }

    @Override
    public LocalDateTime now() {
        return now;
    }

    @Override
    public LocalDateTime dataHoraEntrada() {
        return dataHoraEntrada;
    }

    @Override
    public long minutosEstacionados() {
        return minutosEstacionados;
    }

    @Override
    public boolean tempoAcabando() {
        return minutosEstacionados > MINUTES_TO_NOTIFY;
    }

    @Override
    public void status() {
        if (tempoAcabando()) {
            log.info("[Placa: {}] Você tem menos de 10 minutos para desligar o registro, caso não o faça, será acrescido mais uma hora.",
                    placa());
        }
    }
}
