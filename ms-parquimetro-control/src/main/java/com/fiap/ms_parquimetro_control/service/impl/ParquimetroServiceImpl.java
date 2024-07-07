package com.fiap.ms_parquimetro_control.service.impl;

import com.fiap.ms_parquimetro_control.controller.request.ParkingPerHourRequest;
import com.fiap.ms_parquimetro_control.exception.CarAlreadyParkedException;
import com.fiap.ms_parquimetro_control.repository.EstacionamentoRepository;
import com.fiap.ms_parquimetro_control.repository.entity.Estacionamento;
import com.fiap.ms_parquimetro_control.repository.mapper.EstacionamentoMapper;
import com.fiap.ms_parquimetro_control.service.ParquimetroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if (repository.existsByPlaca(request.getPlaca())) {
            throw new CarAlreadyParkedException(request.getPlaca());
        }

        return repository.insert(estacionamentoMapper.toEstacionamento(request));
    }
}
