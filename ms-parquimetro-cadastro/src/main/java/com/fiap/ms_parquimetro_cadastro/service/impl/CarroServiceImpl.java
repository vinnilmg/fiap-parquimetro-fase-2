package com.fiap.ms_parquimetro_cadastro.service.impl;

import com.fiap.ms_parquimetro_cadastro.controller.mapper.CarroMapper;
import com.fiap.ms_parquimetro_cadastro.controller.response.CarroResponse;
import com.fiap.ms_parquimetro_cadastro.controller.response.MessageResponse;
import com.fiap.ms_parquimetro_cadastro.controller.resquest.CarroRequest;
import com.fiap.ms_parquimetro_cadastro.controller.resquest.CarroUpdateRequest;
import com.fiap.ms_parquimetro_cadastro.exception.carro.CarroNotFoundException;
import com.fiap.ms_parquimetro_cadastro.exception.carro.CarroPlacaNotFoundException;
import com.fiap.ms_parquimetro_cadastro.exception.cliente.ClienteNotFoundException;
import com.fiap.ms_parquimetro_cadastro.exception.carro.PlacaJaUtilizadaException;
import com.fiap.ms_parquimetro_cadastro.repository.CarroRepository;
import com.fiap.ms_parquimetro_cadastro.repository.ClienteRepository;
import com.fiap.ms_parquimetro_cadastro.repository.entity.Carro;
import com.fiap.ms_parquimetro_cadastro.repository.entity.Cliente;
import com.fiap.ms_parquimetro_cadastro.service.CarroService;
import com.fiap.ms_parquimetro_cadastro.utils.BooleanFunctions;
import com.fiap.ms_parquimetro_cadastro.utils.ReflectionMethod;
import com.fiap.ms_parquimetro_cadastro.utils.ValidatePlaca;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
                .orElseThrow(() -> new CarroNotFoundException(UUID));
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
        BooleanFunctions.validateTrue(carroRepository.existsCarroByPlaca(carro.getPlaca()), new PlacaJaUtilizadaException(carro.getPlaca()));

        clienteRepository.findById(fromString(carro.getClienteId())).orElseThrow(() -> {
                    throw new ClienteNotFoundException(carro.getClienteId());
                }
        );


        return carroMapper.CarroEntityToResponse(carroRepository.save(carroMapper.CarroRequestToEntity(carro)));
    }
    @Transactional
    @Override
    public CarroResponse update(String UUID, CarroUpdateRequest carro) throws IllegalAccessException {
        isValidUUID(UUID);
        BooleanFunctions.validateFalse(carroRepository.existsById(fromString(UUID)),new CarroNotFoundException(UUID));
        BooleanFunctions.validateTrue(carroRepository.existsCarroByPlaca(carro.getPlaca()), new PlacaJaUtilizadaException(carro.getPlaca()));
        ValidatePlaca.validatePlacaLenght(carro.getPlaca());

        var carroToUpdate = carroRepository.findById(fromString(UUID)).get();

        ReflectionMethod.updateEntity(carro,carroToUpdate);
        validaCarroUpdateClienteId(carro);
        if(Objects.nonNull(carro.getClienteId())){
            Cliente cliente = clienteRepository.findById(fromString(carro.getClienteId())).get();
            carroToUpdate.setCliente(cliente);
        }
        return carroMapper.CarroEntityToResponse(carroRepository.save(carroToUpdate));
    }

    @Override
    public MessageResponse deleteById(String UUID) {
        isValidUUID(UUID);
        carroRepository.findById(fromString(UUID)).orElseThrow(() -> new CarroNotFoundException(UUID));
        carroRepository.deleteById(fromString(UUID));
        return new MessageResponse(String.format("O carro com o id %s foi deletado com sucesso",UUID));
    }

    private void validaCarroUpdateClienteId(CarroUpdateRequest carro){
        if(Objects.nonNull(carro.getClienteId())){
            isValidUUID(carro.getClienteId());
            BooleanFunctions.validateFalse(clienteRepository.existsById(fromString(carro.getClienteId())),new ClienteNotFoundException(carro.getClienteId()));
        }
    }


}
