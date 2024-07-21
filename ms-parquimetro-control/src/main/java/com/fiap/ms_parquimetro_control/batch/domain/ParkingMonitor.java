package com.fiap.ms_parquimetro_control.batch.domain;

import java.time.LocalDateTime;

public interface ParkingMonitor {
    String placa();

    LocalDateTime now();

    LocalDateTime dataHoraEntrada();

    void status();

    boolean tempoAcabando();
}
