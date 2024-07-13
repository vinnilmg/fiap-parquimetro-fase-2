package com.fiap.ms_parquimetro_cadastro.repository;

import com.fiap.ms_parquimetro_cadastro.repository.entity.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarroRepository extends JpaRepository<Carro, UUID> {
    boolean existsCarroByPlaca(String placa);
}
