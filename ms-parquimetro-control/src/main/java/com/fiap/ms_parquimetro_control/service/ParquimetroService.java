package com.fiap.ms_parquimetro_control.service;

import com.fiap.ms_parquimetro_control.controller.request.*;
import com.fiap.ms_parquimetro_control.repository.db.entity.Estacionamento;

import java.util.List;

public interface ParquimetroService {
    List<Estacionamento> findAll();

    Estacionamento novoEstacionamentoPorHora(ParkingPerHourRequest request);

    Estacionamento novoEstacionamentoFixo(ParkingFixRequest request);

    Estacionamento registrarSaidaEstacionamentoVariavel(ParkingSaidaVariavelRequest request);

    Estacionamento registrarSaidaEstacionamentoFixo(FixedParkingExitRequest request);

    Estacionamento finalizaEstacionamento(FinalizacaoRequest request);

}
