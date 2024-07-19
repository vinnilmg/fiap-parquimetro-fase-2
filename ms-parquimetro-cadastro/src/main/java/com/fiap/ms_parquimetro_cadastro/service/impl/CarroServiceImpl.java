package com.fiap.ms_parquimetro_cadastro.service.impl;

import com.fiap.ms_parquimetro_cadastro.controller.mapper.CarroMapper;
import com.fiap.ms_parquimetro_cadastro.controller.response.CarroResponse;
import com.fiap.ms_parquimetro_cadastro.controller.resquest.CarroRequest;
import com.fiap.ms_parquimetro_cadastro.exception.carro.CarroNotFoundException;
import com.fiap.ms_parquimetro_cadastro.exception.carro.CarroPlacaNotFoundException;
import com.fiap.ms_parquimetro_cadastro.exception.cliente.ClienteNotFoundException;
import com.fiap.ms_parquimetro_cadastro.exception.carro.PlacaJaUtilizadaException;
import com.fiap.ms_parquimetro_cadastro.repository.CarroRepository;
import com.fiap.ms_parquimetro_cadastro.repository.ClienteRepository;
import com.fiap.ms_parquimetro_cadastro.repository.entity.Cliente;
import com.fiap.ms_parquimetro_cadastro.service.CarroService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.fiap.ms_parquimetro_cadastro.utils.ValidateUUID.isValidUUID;
import static java.util.UUID.fromString;

@Service
public class CarroServiceImpl implements CarroService {

    private final CarroRepository carroRepository;
    private final ClienteRepository clienteRepository;
    private final CarroMapper carroMapper;

    public CarroServiceImpl(CarroRepository carroRepository, ClienteRepository clienteRepository, CarroMapper carroMapper) {
        this.carroRepository = carroRepository;
        this.clienteRepository = clienteRepository;
        this.carroMapper = carroMapper;
    }

    @Override
    public CarroResponse findById(String UUID) {
        isValidUUID(UUID);
        return carroRepository.findById(fromString(UUID))
                .map(carroMapper::CarroEntityToResponse)
                .orElseThrow(() -> new EntityNotFoundException(UUID));
    }

    @Override
    public CarroResponse findCarroByPlaca(String placa) {
        return carroRepository.findCarroByPlaca(placa)
                .map(carroMapper::CarroEntityToResponse)
                .orElseThrow(() -> new CarroPlacaNotFoundException(placa));
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
        isValidUUID(carro.getClienteId());
        if (carroRepository.existsCarroByPlaca(Objects.requireNonNull(carro.getPlaca()))) {
            throw new PlacaJaUtilizadaException(carro.getPlaca());
        }
        clienteRepository.findById(fromString(carro.getClienteId())).orElseThrow(() -> {
                    throw new ClienteNotFoundException(carro.getClienteId());
                }
        );


        return carroMapper.CarroEntityToResponse(carroRepository.save(carroMapper.CarroRequestToEntity(carro)));
    }

    @Override
    public CarroResponse update(String UUID, CarroRequest carro) {
        isValidUUID(UUID);
        if (!carroRepository.existsById(fromString(UUID))) {
            throw new CarroNotFoundException(UUID);
        }
        var carroToUpdate = carroRepository.findById(fromString(UUID)).get();
        carroToUpdate.setPlaca(carro.getPlaca());
        carroToUpdate.setMarca(carro.getMarca());
        carroToUpdate.setModelo(carro.getModelo());
        carroToUpdate.setCor(carro.getCor());
        carroToUpdate.setObservacoes(carro.getObservacoes());

        Optional<Cliente> clienteToUpdate = clienteRepository.findById(fromString(carro.getClienteId()));

        clienteToUpdate.ifPresentOrElse((cliente) -> {
            carroToUpdate.setCliente(cliente);
        }, () -> {
            throw new ClienteNotFoundException(carro.getClienteId());
        });

        return carroMapper.CarroEntityToResponse(carroRepository.save(carroToUpdate));
    }

    @Override
    public void deleteById(String UUID) {
        isValidUUID(UUID);
        carroRepository.deleteById(fromString(UUID));
    }
}
