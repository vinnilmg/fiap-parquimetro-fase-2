package com.fiap.ms_parquimetro_control.repository;

import com.fiap.ms_parquimetro_control.repository.entity.Estacionamento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstacionamentoRepository extends MongoRepository<Estacionamento, Long> {
    boolean existsByPlaca(String placa);
}
