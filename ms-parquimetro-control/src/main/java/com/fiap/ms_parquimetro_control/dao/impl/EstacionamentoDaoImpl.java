package com.fiap.ms_parquimetro_control.dao.impl;

import com.fiap.ms_parquimetro_control.dao.EstacionamentoDao;
import com.fiap.ms_parquimetro_control.repository.EstacionamentoRepository;
import com.fiap.ms_parquimetro_control.repository.entity.Estacionamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.fiap.ms_parquimetro_control.repository.enums.StatusEnum.INICIADO;
import static com.fiap.ms_parquimetro_control.repository.enums.StatusEnum.PAGAMENTO_PENDENTE;

@Component
public class EstacionamentoDaoImpl implements EstacionamentoDao {
    @Autowired
    private EstacionamentoRepository repository;

    @Override
    public List<Estacionamento> findOpenedParkingsByPlaca(final String placa) {
        return repository.findByPlacaAndStatusIn(placa, List.of(INICIADO.name(), PAGAMENTO_PENDENTE.name()));
    }

    @Override
    public Optional<Estacionamento> findPendingPaymentParkingByPlaca(final String placa) {
        return repository.findByPlacaAndStatus(placa, PAGAMENTO_PENDENTE.name());
    }

    @Override
    //@Cacheable(cacheNames = "parking", key = "#placa")
    public List<Estacionamento> findByPlaca(final String placa) {
        return repository.findByPlaca(placa);
    }

    @Override
    public List<Estacionamento> findByStatus(final String status) {
        return repository.findByStatus(status);
    }

    @Override
    public List<Estacionamento> findAll() {
        return repository.findAll();
    }

    @Override
    public Estacionamento save(final Estacionamento estacionamento) {
        return repository.save(estacionamento);
    }
}
