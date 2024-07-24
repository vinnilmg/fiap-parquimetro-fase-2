package com.fiap.ms_parquimetro_control.repository.db;

import com.fiap.ms_parquimetro_control.repository.db.entity.Estacionamento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstacionamentoRepository extends MongoRepository<Estacionamento, Long> {
    List<Estacionamento> findByPlaca(String placa);

    List<Estacionamento> findByStatus(String status);

    Optional<Estacionamento> findByPlacaAndStatusIn(String placa, List<String> status);

    Optional<Estacionamento> findByPlacaAndStatus(String placa, String status);
}
