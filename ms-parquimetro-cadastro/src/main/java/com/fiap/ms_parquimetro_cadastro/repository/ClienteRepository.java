package com.fiap.ms_parquimetro_cadastro.repository;

import com.fiap.ms_parquimetro_cadastro.repository.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    boolean existsClienteByCnh(String cnh);

    Optional<Cliente> findClienteByCnh(String cnh);

}
