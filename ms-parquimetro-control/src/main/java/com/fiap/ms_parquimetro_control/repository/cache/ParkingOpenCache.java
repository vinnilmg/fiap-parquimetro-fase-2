package com.fiap.ms_parquimetro_control.repository.cache;

import com.fiap.ms_parquimetro_control.repository.db.entity.Estacionamento;

import java.util.Optional;

public interface ParkingOpenCache {
    Optional<Estacionamento> find(String placa);

    Estacionamento save(String placa, Estacionamento estacionamento);

    void delete(String placa);
}
