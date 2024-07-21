package com.fiap.ms_parquimetro_control.batch.domain;

import com.fiap.ms_parquimetro_control.repository.entity.Estacionamento;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

import static com.fiap.ms_parquimetro_control.utils.DateUtils.calculaHorasEntreDatas;

@Slf4j
public class ParkingMonitorToFixedTimeImpl implements ParkingMonitorToFixedTime {
    private final String placa;
    private final LocalDateTime now;
    private final LocalDateTime dataHoraEntrada;
    private final int horasSolicitadas;
    private final long horasEstacionadas;

    public ParkingMonitorToFixedTimeImpl(final Estacionamento parking) {
        this.now = LocalDateTime.now();
        this.placa = parking.getPlaca();
        this.horasSolicitadas = parking.getTempoFixo();
        this.dataHoraEntrada = parking.getDataHoraEntrada();
        this.horasEstacionadas = calculaHorasEntreDatas(dataHoraEntrada, now);
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
    public long horasEstacionadas() {
        return horasEstacionadas;
    }

    @Override
    public void status() {
        if (tempoAcabando()) {
            log.info("[Placa: {}] Seu tempo está acabando, resta somente 1 hora!", placa());
        } else if (tempoAcabou()) {
            log.info("[Placa: {}] Seu tempo acabou, iniciando cobrança por hora!", placa());
        } else if (tempoUltrapassado()) {
            log.info("[Placa: {}] Seu tempo acabou faz um tempo, a cobrança por hora está ativa!!", placa());
        }
    }

    @Override
    public int horasSolicitadas() {
        return horasSolicitadas;
    }

    @Override
    public boolean tempoUltrapassado() {
        return horasEstacionadas > horasSolicitadas;
    }

    @Override
    public boolean tempoAcabou() {
        return horasEstacionadas == horasSolicitadas;
    }

    @Override
    public boolean tempoAcabando() {
        return (horasEstacionadas + 1) == horasSolicitadas;
    }
}
