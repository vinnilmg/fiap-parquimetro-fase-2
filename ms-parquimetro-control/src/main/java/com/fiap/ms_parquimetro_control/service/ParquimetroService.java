package com.fiap.ms_parquimetro_control.service;

import com.fiap.ms_parquimetro_control.controller.request.FinalizacaoRequest;
import com.fiap.ms_parquimetro_control.controller.request.ParkingPerHourRequest;
import com.fiap.ms_parquimetro_control.repository.entity.Estacionamento;

import java.util.List;

public interface ParquimetroService {
    List<Estacionamento> findAll();

    Estacionamento novoEstacionamentoPorHora(ParkingPerHourRequest request);

    Estacionamento finalizaEstacionamento(FinalizacaoRequest request);
}
