package com.fiap.ms_parquimetro_control.batch;

import com.fiap.ms_parquimetro_control.repository.EstacionamentoRepository;
import com.fiap.ms_parquimetro_control.repository.entity.Estacionamento;
import com.fiap.ms_parquimetro_control.repository.enums.StatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.fiap.ms_parquimetro_control.repository.enums.TipoEstacionamentoEnum.FIXO;
import static com.fiap.ms_parquimetro_control.utils.DateUtils.calculaHorasEntreDatas;

@Slf4j
@Service
public class ParkingMonitor {
    @Autowired
    private EstacionamentoRepository repository;

    @Scheduled(fixedDelay = 30000, initialDelay = 5000)
    private void monitoring() {
        log.info("Iniciando monitoramento...");
        repository.findByStatus(StatusEnum.INICIADO.name())
                .forEach(parking -> {
                    if (parking.getTipo().equals(FIXO)) {
                        fixo(parking);
                    } else {
                        hora(parking);
                    }
                });
    }

    private static void fixo(final Estacionamento parking) {
        final var now = LocalDateTime.now();//.plusHours(3);
        final var tempoSolicitado = parking.getTempoFixo();
        final var horasEstacionadas = calculaHorasEntreDatas(parking.getDataHoraEntrada(), now);

        if (horasEstacionadas == tempoSolicitado) {
            log.info("[Placa: {}] Seu tempo acabou, iniciando cobrança por hora!", parking.getPlaca());
        } else if (horasEstacionadas > tempoSolicitado) {
            log.info("[Placa: {}] Seu tempo acabou faz um tempo, a cobrança por hora está ativa!!", parking.getPlaca());
        } else {
            if ((horasEstacionadas + 1) == tempoSolicitado) {
                log.info("[Placa: {}] Seu tempo está acabando, resta somente 1 hora!", parking.getPlaca());
            }
        }
    }

    private static void hora(final Estacionamento parking) {
    }
}
