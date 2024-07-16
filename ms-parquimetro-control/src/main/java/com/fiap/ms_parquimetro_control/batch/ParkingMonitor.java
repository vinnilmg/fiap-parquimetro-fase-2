package com.fiap.ms_parquimetro_control.batch;

import com.fiap.ms_parquimetro_control.repository.EstacionamentoRepository;
import com.fiap.ms_parquimetro_control.repository.enums.StatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ParkingMonitor {

    @Autowired
    private EstacionamentoRepository repository;

    @Scheduled(fixedDelay = 60000, initialDelay = 5000)
    private void monitoring() {
        log.info("Iniciando monitoramento...");
        repository.findByStatus(StatusEnum.INICIADO.name())
                .forEach(parking -> log.info(parking.toString()));
    }
}
