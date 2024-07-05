package com.fiap.ms_parquimetro_control.repository;

import com.fiap.ms_parquimetro_control.repository.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoRepositoryTest extends MongoRepository<User, Long> {
}
