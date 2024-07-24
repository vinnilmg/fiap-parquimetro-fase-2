package com.fiap.ms_parquimetro_control.repository.cache;

import com.fiap.ms_parquimetro_control.repository.db.entity.Estacionamento;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.fiap.ms_parquimetro_control.constants.CacheConstants.PARKING_CACHE_KEY;
import static com.fiap.ms_parquimetro_control.constants.CacheConstants.PARKING_OPEN_CACHE_NAME;

@Repository
public class ParkingOpenCacheImpl implements ParkingOpenCache {

    @Cacheable(cacheNames = PARKING_OPEN_CACHE_NAME, key = PARKING_CACHE_KEY, unless = "#result == null")
    @Override
    public Optional<Estacionamento> find(String placa) {
        return Optional.empty();
    }

    @Cacheable(cacheNames = PARKING_OPEN_CACHE_NAME, key = PARKING_CACHE_KEY, unless = "#result == null")
    @Override
    public Estacionamento save(String placa, Estacionamento estacionamento) {
        return estacionamento;
    }

    @CacheEvict(value = PARKING_OPEN_CACHE_NAME, key = PARKING_CACHE_KEY)
    @Override
    public void delete(String placa) {
        // delete
    }
}
