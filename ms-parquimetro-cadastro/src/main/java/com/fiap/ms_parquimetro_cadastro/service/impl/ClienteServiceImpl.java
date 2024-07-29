package com.fiap.ms_parquimetro_cadastro.service.impl;

import com.fiap.ms_parquimetro_cadastro.controller.mapper.ClienteMapper;
import com.fiap.ms_parquimetro_cadastro.controller.response.ClienteResponse;
import com.fiap.ms_parquimetro_cadastro.controller.response.MessageResponse;
import com.fiap.ms_parquimetro_cadastro.controller.resquest.ClienteRequest;
import com.fiap.ms_parquimetro_cadastro.exception.carro.CarroNotFoundException;
import com.fiap.ms_parquimetro_cadastro.exception.cliente.ClienteCnhNotFoundException;
import com.fiap.ms_parquimetro_cadastro.exception.cliente.ClienteNotFoundException;
import com.fiap.ms_parquimetro_cadastro.exception.cliente.CnhJaUtilizadaException;
import com.fiap.ms_parquimetro_cadastro.exception.cliente.UUIDClienteInvalidException;
import com.fiap.ms_parquimetro_cadastro.repository.CarroRepository;
import com.fiap.ms_parquimetro_cadastro.repository.ClienteRepository;
import com.fiap.ms_parquimetro_cadastro.service.ClienteService;
import com.fiap.ms_parquimetro_cadastro.utils.BooleanFunctions;
import com.fiap.ms_parquimetro_cadastro.utils.ReflectionMethod;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.Objects;
import static com.fiap.ms_parquimetro_cadastro.utils.ValidateUUID.isValidUUID;


import static java.util.UUID.fromString;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;
    private final CarroRepository carroRepository;
    public ClienteServiceImpl(ClienteRepository clienteRepository, ClienteMapper clienteMapper, CarroRepository carroRepository) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
        this.carroRepository = carroRepository;
    }

    @Override
    public List<ClienteResponse> findAll() {
        return clienteRepository.findAll()
                .stream()
                .map(clienteMapper::ClienteEntityToResponse)
                .toList();
    }

    @Override
    public ClienteResponse findById(String UUID) {
        isValidUUID(UUID);
        return clienteRepository.findById(fromString(UUID))
                .map(clienteMapper::ClienteEntityToResponse)
                .orElseThrow(() -> new ClienteNotFoundException(UUID));
    }

    @Override
    public ClienteResponse findClienteByCnh(String cnh) {
        return clienteRepository.findClienteByCnh(cnh)
                .map(clienteMapper::ClienteEntityToResponse)
                .orElseThrow(() -> new ClienteCnhNotFoundException(cnh));
    }

    @Override
    public ClienteResponse save(ClienteRequest cliente) {
        BooleanFunctions.validateTrue(clienteRepository.existsClienteByCnh(cliente.getCnh()), new CnhJaUtilizadaException(cliente.getCnh()));

        return clienteMapper.ClienteEntityToResponse(
                clienteRepository.save
                        (clienteMapper.ClienteRequestToEntity(cliente)));
    }

    @Override
    public ClienteResponse update(String UUID, ClienteRequest cliente) {
        isValidUUID(UUID);
        BooleanFunctions.validateFalse(clienteRepository.existsById(fromString(UUID)), new ClienteNotFoundException(UUID));
        BooleanFunctions.validateTrue(clienteRepository.existsClienteByCnh(cliente.getCnh()), new CnhJaUtilizadaException(cliente.getCnh()));

        var clienteToUpdate = clienteRepository.findById(fromString(UUID)).get();
        ReflectionMethod.updateEntity(cliente, clienteToUpdate);

        return clienteMapper.ClienteEntityToResponse(clienteRepository.save(clienteToUpdate));
    }

    @Override
    @Transactional
    public MessageResponse deleteById(String UUID) {
        isValidUUID(UUID);
        carroRepository.findById(fromString(UUID)).orElseThrow(() -> new CarroNotFoundException(UUID));
        clienteRepository.findById(fromString(UUID)).orElseThrow(() -> new ClienteNotFoundException(UUID));
        clienteRepository.deleteById(fromString(UUID));
        return new MessageResponse(String.format("O cliente com o id %s foi deletado com sucesso.", UUID));
    }



}
