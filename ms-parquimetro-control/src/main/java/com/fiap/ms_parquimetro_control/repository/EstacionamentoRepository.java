package com.fiap.ms_parquimetro_control.repository;

import com.fiap.ms_parquimetro_control.repository.entity.Estacionamento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstacionamentoRepository extends MongoRepository<Estacionamento, Long> {
    List<Estacionamento> findByPlaca(String placa);
}
