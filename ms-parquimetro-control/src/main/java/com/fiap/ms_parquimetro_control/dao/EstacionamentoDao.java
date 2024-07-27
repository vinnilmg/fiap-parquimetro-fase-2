package com.fiap.ms_parquimetro_control.dao;

import com.fiap.ms_parquimetro_control.repository.db.entity.Estacionamento;

import java.util.List;
import java.util.Optional;

public interface EstacionamentoDao {
    Optional<Estacionamento> findOpenedParkingByPlaca(String placa);

    Optional<Estacionamento> findFixedExitParkingByPlaca(String placa);

    Optional<Estacionamento> findPendingPaymentParkingByPlaca(String placa);

    List<Estacionamento> findByPlaca(String placa);

    List<Estacionamento> findByStatus(String status);

    List<Estacionamento> findAll();

    Estacionamento save(Estacionamento estacionamento);
}
