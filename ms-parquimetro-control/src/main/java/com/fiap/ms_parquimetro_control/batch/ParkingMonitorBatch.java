package com.fiap.ms_parquimetro_control.batch;

import com.fiap.ms_parquimetro_control.batch.domain.ParkingMonitorToFixedTimeImpl;
import com.fiap.ms_parquimetro_control.batch.domain.ParkingMonitorToVariableTimeImpl;
import com.fiap.ms_parquimetro_control.dao.EstacionamentoDao;
import com.fiap.ms_parquimetro_control.repository.enums.StatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import static com.fiap.ms_parquimetro_control.repository.enums.TipoEstacionamentoEnum.FIXO;

@Slf4j
@Service
public class ParkingMonitorBatch {
    @Autowired
    private EstacionamentoDao dao;

    @Scheduled(fixedDelay = 60000, initialDelay = 5000)
    private void monitoring() {
        log.info("Iniciando monitoramento dos estacionamentos em aberto...");
        dao.findByStatus(StatusEnum.INICIADO.name())
                .forEach(parking -> (parking.getTipo().equals(FIXO)
                        ? new ParkingMonitorToFixedTimeImpl(parking)
                        : new ParkingMonitorToVariableTimeImpl(parking))
                        .status());
    }
}
