package com.fiap.ms_parquimetro_control.service.impl;

import com.fiap.ms_parquimetro_control.client.MsParquimetroCadastroClient;
import com.fiap.ms_parquimetro_control.controller.request.*;
import com.fiap.ms_parquimetro_control.dao.EstacionamentoDao;
import com.fiap.ms_parquimetro_control.exception.*;
import com.fiap.ms_parquimetro_control.exception.ParkingNotFoundException;
import com.fiap.ms_parquimetro_control.repository.cache.ParkingOpenCache;
import com.fiap.ms_parquimetro_control.repository.cache.ParkingPendingPaymentCache;
import com.fiap.ms_parquimetro_control.repository.db.entity.Estacionamento;
import com.fiap.ms_parquimetro_control.repository.db.enums.StatusEnum;
import com.fiap.ms_parquimetro_control.repository.db.enums.TipoPagamentoEnum;
import com.fiap.ms_parquimetro_control.repository.db.mapper.EstacionamentoMapper;
import com.fiap.ms_parquimetro_control.client.dto.CarroDTO;
import com.fiap.ms_parquimetro_control.client.dto.ClienteDTO;
import com.fiap.ms_parquimetro_control.service.ParquimetroService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.fiap.ms_parquimetro_control.constants.ParquimetroConstants.VALOR_HORA_TIPO_FIXO;
import static com.fiap.ms_parquimetro_control.constants.ParquimetroConstants.VALOR_HORA_TIPO_VARIAVEL;
import static com.fiap.ms_parquimetro_control.repository.db.enums.TipoEstacionamentoEnum.FIXO;
import static com.fiap.ms_parquimetro_control.repository.db.enums.TipoEstacionamentoEnum.HORA;
import static com.fiap.ms_parquimetro_control.repository.db.enums.TipoPagamentoEnum.PIX;
import static com.fiap.ms_parquimetro_control.utils.DateUtils.calculaHorasEntreDatas;

@Slf4j
@Service
public class ParquimetroServiceImpl implements ParquimetroService {
    @Autowired
    private EstacionamentoDao dao;

    @Autowired
    private ParkingOpenCache parkingOpenCache;

    @Autowired
    private ParkingPendingPaymentCache parkingPendingPaymentCache;

    @Autowired
    private EstacionamentoMapper estacionamentoMapper;

    @Autowired
    private MsParquimetroCadastroClient msParquimetroCadastroClient;

    @Override
    public List<Estacionamento> findAll() {
        return dao.findAll();
    }

    @Override
    public Estacionamento novoEstacionamentoPorHora(final ParkingPerHourRequest request) {
        getOpenedParking(request.getPlaca())
                .ifPresent(parking -> {
                    throw new CarAlreadyParkedException(parking.getPlaca());
                });

        final var parking = dao.save(estacionamentoMapper.toEstacionamento(request));
        return parkingOpenCache.save(parking.getPlaca(), parking);
    }

    @Override
    public Estacionamento novoEstacionamentoFixo(final ParkingFixRequest request) {
        CarroDTO carro = msParquimetroCadastroClient.getCarroByPlaca(request.getPlaca());
        ClienteDTO cliente = msParquimetroCadastroClient.getClienteById(carro.getClienteId());

        request.setPagamento(cliente.getFormaPagamentoPreferida());

        if (request.getTempoFixo().compareTo(1) != 1) throw new InvalidTimeFixException();

        getOpenedParking(request.getPlaca())
                .ifPresent(parking -> {
                    throw new CarAlreadyParkedException(parking.getPlaca());
                });

        final var parking = dao.save(estacionamentoMapper.toEstacionamento(request));
        return parkingOpenCache.save(parking.getPlaca(), parking);
    }

    @Override
    public Estacionamento registrarSaidaEstacionamentoVariavel(ParkingSaidaVariavelRequest request) {
        return getOpenedParking(request.getPlaca())
                .map(parking -> {
                    if (!parking.getTipo().equals(HORA)) throw new InvalidParkingTypeException();
                    final var estacionamento = dao.save(estacionamentoMapper.toEstacionamentoSaidaVariavel(parking));
                    parkingOpenCache.delete(request.getPlaca());
                    return parkingPendingPaymentCache.save(request.getPlaca(), estacionamento);
                })
                .orElseThrow(ParkingNotFoundException::new);
    }

    @Override
    public Estacionamento registrarSaidaEstacionamentoFixo(final FixedParkingExitRequest request) {
        return getOpenedParking(request.getPlaca())
                .map(parking -> {
                    if (!parking.getTipo().equals(FIXO)) throw new InvalidParkingTypeException();
                    parking.setDataHoraSaida(LocalDateTime.now());
                    parking.setStatus(StatusEnum.PAGAMENTO_PENDENTE);
                    parking.setHorasExcedentes((int) calculaHorasEstacionadasAdicional(parking));
                    parking.setValorCalculado(BigDecimal.valueOf(calculaHorasEstacionadasFixa(parking)));
                    parking.setHorasEstacionadas(parking.getTempoFixo() + parking.getHorasExcedentes());

                    parkingOpenCache.delete(parking.getPlaca());
                    final var parkingUpdated = dao.save(parking);
                    return parkingPendingPaymentCache.save(parking.getPlaca(), parkingUpdated);
                })
                .orElseThrow(ParkingNotFoundException::new);
    }

    @Override
    public Estacionamento finalizaEstacionamento(final FinalizacaoRequest request) {
        final var tipoPagamento = TipoPagamentoEnum.toEnum(request.getTipoPagamento());
        return getPendingPaymentParking(request.getPlaca())
                .map(parking -> {
                    if (tipoPagamento.equals(PIX) && !parking.getTipo().equals(FIXO)) {
                        throw new InvalidPaymentTypePixException();
                    }

                    parking.finalizaEstacionamento(tipoPagamento);
                    parkingPendingPaymentCache.delete(request.getPlaca());
                    return dao.save(parking);
                })
                .orElseThrow(InvalidParkingStatusException::new);
    }

    private Optional<Estacionamento> getPendingPaymentParking(final String placa) {
        return parkingPendingPaymentCache.find(placa)
                .or(() -> dao.findPendingPaymentParkingByPlaca(placa));
    }

    private Optional<Estacionamento> getOpenedParking(final String placa) {
        return parkingOpenCache.find(placa)
                .or(() -> dao.findOpenedParkingByPlaca(placa)
                        .map(parking -> parkingOpenCache.save(parking.getPlaca(), parking)));
    }

    private long calculaHorasEstacionadasFixa(final Estacionamento estacionamento) {
        long horasTotais = calculaHorasEntreDatas(estacionamento.getDataHoraEntrada(), estacionamento.getDataHoraSaida());
        long horasFixa = estacionamento.getTempoFixo();
        long horasFixaCalculadas = calculaHorasEntreDatas(
                estacionamento.getDataHoraEntrada(),
                estacionamento.getDataHoraEntrada().plusHours(horasFixa));

        BigDecimal valorTotal;
        if (horasTotais <= horasFixaCalculadas) {
            valorTotal = VALOR_HORA_TIPO_FIXO.multiply(BigDecimal.valueOf(horasFixa));
        } else {
            BigDecimal valorFixo = VALOR_HORA_TIPO_FIXO.multiply(BigDecimal.valueOf(horasFixa));
            long horasAdicionais = calculaHorasEntreDatas(
                    estacionamento.getDataHoraEntrada().plusHours(horasFixa),
                    estacionamento.getDataHoraSaida());
            BigDecimal valorAdicional = VALOR_HORA_TIPO_VARIAVEL.multiply(BigDecimal.valueOf(horasAdicionais));
            valorTotal = valorFixo.add(valorAdicional);
        }

        return valorTotal.longValue();
    }

    private long calculaHorasEstacionadasAdicional(final Estacionamento estacionamento) {
        var horaAdicional = estacionamento.getDataHoraEntrada().plusHours(estacionamento.getTempoFixo());
        var valorCalculoAdicional = calculaHorasEntreDatas(horaAdicional, estacionamento.getDataHoraSaida());
        return Math.max(valorCalculoAdicional, 0);
    }
}
