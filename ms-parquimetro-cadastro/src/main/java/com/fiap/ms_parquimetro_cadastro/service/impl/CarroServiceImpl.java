package com.fiap.ms_parquimetro_cadastro.service.impl;

import com.fiap.ms_parquimetro_cadastro.controller.mapper.CarroMapper;
import com.fiap.ms_parquimetro_cadastro.controller.response.CarroResponse;
import com.fiap.ms_parquimetro_cadastro.controller.resquest.CarroRequest;
import com.fiap.ms_parquimetro_cadastro.repository.CarroRepository;
import com.fiap.ms_parquimetro_cadastro.service.CarroService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static java.util.UUID.fromString;
@Service
public class CarroServiceImpl implements CarroService {

    private final CarroRepository carroRepository;
    private CarroMapper carroMapper;

    public CarroServiceImpl(CarroRepository carroRepository) {
        this.carroRepository = carroRepository;
    }

    @Override
    public CarroResponse findById(String UUID) {
        return carroRepository.findById(fromString(UUID))
                .map(carroMapper::CarroEntityToResponse)
                .orElseThrow(() -> new EntityNotFoundException(UUID));
    }

    @Override
    public List<CarroResponse> findAll() {
        return carroRepository.findAll()
                .stream()
                .map(carroMapper::CarroEntityToResponse)
                .toList();
    }

    @Override
    public CarroResponse save(CarroRequest carro) {
        if(carroRepository.existsCarroByPlaca(Objects.requireNonNull(carro.getPlaca()))) {
            // implementar exception costumizada
            throw new RuntimeException("já existe carro com essa placa");
        }
        return carroMapper.CarroEntityToResponse(carroRepository.save(carroMapper.CarroRequestToEntity(carro)));
    }

    @Override
    public CarroResponse update(String UUID, CarroRequest carro) {
        if(carroRepository.existsById(fromString(UUID))) {
            // implementar exception costumizada
            throw new RuntimeException("carro inexistente");
        }
       var carroToUpdate = carroRepository.findById(fromString(UUID)).get();
        carroToUpdate.setPlaca(carro.getPlaca());
        carroToUpdate.setMarca(carro.getMarca());
        carroToUpdate.setModelo(carro.getModelo());
        carroToUpdate.setCor(carro.getCor());
        return carroMapper.CarroEntityToResponse(carroRepository.save(carroToUpdate));
    }

    @Override
    public void deleteById(String UUID) {
        carroRepository.deleteById(fromString(UUID));
    }
}
