package com.fiap.ms_parquimetro_control.service.impl;

import com.fiap.ms_parquimetro_control.controller.request.FinalizacaoRequest;
import com.fiap.ms_parquimetro_control.controller.request.ParkingFixRequest;
import com.fiap.ms_parquimetro_control.controller.request.ParkingPerHourRequest;
import com.fiap.ms_parquimetro_control.controller.request.ParkingSaidaVariavelRequest;
import com.fiap.ms_parquimetro_control.exception.*;
import com.fiap.ms_parquimetro_control.repository.EstacionamentoRepository;
import com.fiap.ms_parquimetro_control.repository.entity.Estacionamento;
import com.fiap.ms_parquimetro_control.repository.enums.StatusEnum;
import com.fiap.ms_parquimetro_control.repository.enums.TipoPagamentoEnum;
import com.fiap.ms_parquimetro_control.repository.mapper.EstacionamentoMapper;
import com.fiap.ms_parquimetro_control.service.ParquimetroService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.fiap.ms_parquimetro_control.repository.enums.StatusEnum.PAGAMENTO_PENDENTE;
import static com.fiap.ms_parquimetro_control.repository.enums.TipoEstacionamentoEnum.FIXO;
import static com.fiap.ms_parquimetro_control.repository.enums.TipoPagamentoEnum.PIX;

@Slf4j
@Service
public class ParquimetroServiceImpl implements ParquimetroService {
    @Autowired
    private EstacionamentoRepository repository;

    @Autowired
    private EstacionamentoMapper estacionamentoMapper;

    @Override
    public List<Estacionamento> findAll() {
        return repository.findAll();
    }

    @Override
    public Estacionamento novoEstacionamentoPorHora(final ParkingPerHourRequest request) {
        if (existsEstacionamentoEmAberto(request.getPlaca())) {
            throw new CarAlreadyParkedException(request.getPlaca());
        }
        return repository.insert(estacionamentoMapper.toEstacionamento(request));
    }

    @Override
    public Estacionamento novoEstacionamentoFixo(final ParkingFixRequest request) {
        // TODO: Integracao com o serviço de cadastro utilizando a placa
        // Se nao estiver cadastrado -> Erro
        // Se existir: Pegar a forma de pagamento preferida que está no cadastro do cliente e inserir no ticket (campo pagamento)

        if (!(request.getTempoFixo() > 1)) {
            throw new InvalidTimeFix(); //
        }

        if (existsEstacionamentoEmAberto(request.getPlaca())) {
            throw new CarAlreadyParkedException(request.getPlaca());
        }
        return repository.insert(estacionamentoMapper.toEstacionamento(request));
    }

    @Override
    public Estacionamento registrarSaidaVariavel(ParkingSaidaVariavelRequest request) {
        return repository.findByPlaca(request.getPlaca())
                .stream()
                .filter(parking -> parking.getStatus().equals(StatusEnum.INICIADO))
                .findFirst()
                .map(parking -> {
                    return repository.save(estacionamentoMapper.toEstacionamentoSaidaVariavel(parking));
                })
                .orElseThrow(ParkingNotFoundException::new);
    }

    @Override
    public Estacionamento finalizaEstacionamento(final FinalizacaoRequest request) {
        final var tipoPagamento = TipoPagamentoEnum.toEnum(request.getTipoPagamento());
        return repository.findByPlaca(request.getPlaca())
                .stream()
                .filter(parking -> !parking.getStatus().equals(StatusEnum.FINALIZADO))
                .findFirst()
                .map(parking -> {
                    if (!parking.getStatus().equals(PAGAMENTO_PENDENTE)) {
                        throw new InvalidParkingStatusException();
                    }

                    if (tipoPagamento.equals(PIX) && !parking.getTipo().equals(FIXO)) {
                        throw new InvalidPaymentTypePix();
                    }

                    parking.finalizaEstacionamento(tipoPagamento);
                    return repository.save(parking);
                })
                .orElseThrow(ParkingNotFoundException::new);
    }

    private boolean existsEstacionamentoEmAberto(final String placa) {
        return repository.findByPlaca(placa)
                .stream()
                .anyMatch(estacionamento -> !estacionamento.getStatus().equals(StatusEnum.FINALIZADO));
    }
}
