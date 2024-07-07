package com.fiap.ms_parquimetro_control.repository;

import com.fiap.ms_parquimetro_control.repository.entity.Estacionamento;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoRepository extends org.springframework.data.mongodb.repository.MongoRepository<Estacionamento, String> {
}
