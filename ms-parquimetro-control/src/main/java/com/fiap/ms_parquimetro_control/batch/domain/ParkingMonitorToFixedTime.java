package com.fiap.ms_parquimetro_control.batch.domain;

public interface ParkingMonitorToFixedTime extends ParkingMonitor {
    long horasEstacionadas();

    int horasSolicitadas();

    boolean tempoUltrapassado();

    boolean tempoAcabou();
}
