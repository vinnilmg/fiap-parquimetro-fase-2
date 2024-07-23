package com.fiap.ms_parquimetro_control.batch;

import com.fiap.ms_parquimetro_control.batch.domain.ParkingMonitorToFixedTimeImpl;
import com.fiap.ms_parquimetro_control.batch.domain.ParkingMonitorToVariableTimeImpl;
import com.fiap.ms_parquimetro_control.dao.EstacionamentoDao;
import com.fiap.ms_parquimetro_control.repository.db.enums.StatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import static com.fiap.ms_parquimetro_control.repository.db.enums.TipoEstacionamentoEnum.FIXO;

@Slf4j
@Service
public class ParkingMonitorBatch {
    @Autowired
    private EstacionamentoDao dao;

    @Value("${monitor.batch.active:true}")
    private boolean active;

    @Scheduled(fixedDelay = 60000, initialDelay = 5000)
    private void monitoring() {
        if (active) {
            process();
        }
    }

    private void process() {
        log.info("Iniciando monitoramento dos estacionamentos em aberto...");
        dao.findByStatus(StatusEnum.INICIADO.name())
                .forEach(parking -> (parking.getTipo().equals(FIXO)
                        ? new ParkingMonitorToFixedTimeImpl(parking)
                        : new ParkingMonitorToVariableTimeImpl(parking))
                        .status());
    }
}
