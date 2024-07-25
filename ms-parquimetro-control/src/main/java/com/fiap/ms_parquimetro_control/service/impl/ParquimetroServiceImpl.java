package com.fiap.ms_parquimetro_control.service.impl;

import com.fiap.ms_parquimetro_control.controller.request.*;
import com.fiap.ms_parquimetro_control.dao.EstacionamentoDao;
import com.fiap.ms_parquimetro_control.exception.*;
import com.fiap.ms_parquimetro_control.repository.cache.ParkingOpenCache;
import com.fiap.ms_parquimetro_control.repository.cache.ParkingPendingPaymentCache;
import com.fiap.ms_parquimetro_control.repository.db.entity.Estacionamento;
import com.fiap.ms_parquimetro_control.repository.db.enums.FormaPagamentoPreferidaEnum;
import com.fiap.ms_parquimetro_control.repository.db.enums.StatusEnum;
import com.fiap.ms_parquimetro_control.repository.db.enums.TipoPagamentoEnum;
import com.fiap.ms_parquimetro_control.repository.db.mapper.EstacionamentoMapper;
import com.fiap.ms_parquimetro_control.client.MsParquimetroCadastroClient;
import com.fiap.ms_parquimetro_control.controller.request.FinalizacaoRequest;
import com.fiap.ms_parquimetro_control.controller.request.ParkingFixRequest;
import com.fiap.ms_parquimetro_control.controller.request.ParkingPerHourRequest;
import com.fiap.ms_parquimetro_control.controller.request.ParkingSaidaVariavelRequest;
import com.fiap.ms_parquimetro_control.repository.dto.CarroDTO;
import com.fiap.ms_parquimetro_control.repository.dto.ClienteDTO;
import com.fiap.ms_parquimetro_control.service.ParquimetroService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.fiap.ms_parquimetro_control.repository.db.enums.TipoEstacionamentoEnum.FIXO;
import static com.fiap.ms_parquimetro_control.repository.db.enums.TipoPagamentoEnum.PIX;

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
        // TODO: Integracao com o serviço de cadastro utilizando a placa
        CarroDTO carro = msParquimetroCadastroClient.getCarroByPlaca(request.getPlaca());

        ClienteDTO cliente = msParquimetroCadastroClient.getClienteById(carro.getClienteId());
        // Se nao estiver cadastrado -> Erro
        // Se existir: Pegar a forma de pagamento preferida que está no cadastro do cliente e inserir no ticket (campo pagamento)
        request.setPagamento(cliente.getFormaPagamentoPreferida());
        if (!(request.getTempoFixo() > 1)) {
            throw new InvalidTimeFix(); //
        }

        if (existsEstacionamentoEmAberto(request.getPlaca())) {
            throw new CarAlreadyParkedException(request.getPlaca());
        }
        return dao.save(estacionamentoMapper.toEstacionamento(request));
    }

    @Override
    public Estacionamento registrarSaidaVariavel(ParkingSaidaVariavelRequest request) {
        return dao.findByPlaca(request.getPlaca())
                .stream()
                .filter(parking -> parking.getStatus().equals(StatusEnum.INICIADO))
                .findFirst()
                .map(parking -> {
                    return dao.save(estacionamentoMapper.toEstacionamentoSaidaVariavel(parking));
                })
                .orElseThrow(ParkingNotFoundException::new);
    }

    @Override
    public Estacionamento finalizaEstacionamento(final FinalizacaoRequest request) {
        final var tipoPagamento = TipoPagamentoEnum.toEnum(request.getTipoPagamento());
        return getPendingPaymentParking(request.getPlaca())
                .map(parking -> {
                    if (tipoPagamento.equals(PIX) && !parking.getTipo().equals(FIXO)) {
                        throw new InvalidPaymentTypePix();
                    }

                    parking.finalizaEstacionamento(tipoPagamento);
                    parkingPendingPaymentCache.delete(request.getPlaca());
                    return dao.save(parking);
                })
                .orElseThrow(InvalidParkingStatusException::new);
    }

    public Estacionamento saidaEstacionamentoFixo(final FixedParkingExitRequest request) {
        //return repository.findByPlaca(request.getPlaca()).stream().filter(estacionamento -> !estacionamento.getStatus().equals(StatusEnum.FINALIZADO)).findFirst().orElseThrow(ParkingNotFoundException::new);
        return null;
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

    private boolean existsEstacionamentoEmAberto(final String placa) {
        return dao.findByPlaca(placa)
                .stream()
                .anyMatch(estacionamento -> !estacionamento.getStatus().equals(StatusEnum.FINALIZADO));
    }
}
