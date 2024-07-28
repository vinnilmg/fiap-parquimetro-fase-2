package com.fiap.ms_parquimetro_cadastro.service.impl;

import com.fiap.ms_parquimetro_cadastro.controller.mapper.ClienteMapper;
import com.fiap.ms_parquimetro_cadastro.controller.response.ClienteResponse;
import com.fiap.ms_parquimetro_cadastro.controller.resquest.ClienteRequest;
import com.fiap.ms_parquimetro_cadastro.exception.cliente.ClienteCnhNotFoundException;
import com.fiap.ms_parquimetro_cadastro.exception.cliente.CnhJaUtilizadaException;
import com.fiap.ms_parquimetro_cadastro.exception.cliente.UUIDClienteInvalidException;
import com.fiap.ms_parquimetro_cadastro.repository.CarroRepository;
import com.fiap.ms_parquimetro_cadastro.repository.ClienteRepository;
import com.fiap.ms_parquimetro_cadastro.service.ClienteService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
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
                .orElseThrow(() -> new UUIDClienteInvalidException(UUID));
    }

    @Override
    public ClienteResponse findClienteByCnh(String cnh) {
        return clienteRepository.findClienteByCnh(cnh)
                .map(clienteMapper::ClienteEntityToResponse)
                .orElseThrow(() -> new ClienteCnhNotFoundException(cnh));
    }

    @Override
    public ClienteResponse save(ClienteRequest cliente) {
        if (clienteRepository.existsClienteByCnh(Objects.requireNonNull(cliente.getCnh()))) {
            throw new CnhJaUtilizadaException(cliente.getCnh());
        }

        return clienteMapper.ClienteEntityToResponse(
                clienteRepository.save
                        (clienteMapper.ClienteRequestToEntity(cliente)));
    }

    @Override
    public ClienteResponse update(String UUID, ClienteRequest cliente) {
        if (!clienteRepository.existsById(fromString(UUID))) {
            throw new UUIDClienteInvalidException(UUID);
        }

        var clienteToUpdate = clienteRepository.findById(fromString(UUID)).get();
        clienteToUpdate.setNome(cliente.getNome());
        clienteToUpdate.setCnh(cliente.getCnh());
        clienteToUpdate.setTelefone(cliente.getTelefone());
        clienteToUpdate.setEmail(cliente.getEmail());
        clienteToUpdate.setFormaPagamentoPreferida(cliente.getFormaPagamentoPreferida());

        return clienteMapper.ClienteEntityToResponse(clienteRepository.save(clienteToUpdate));
    }

    @Override
    @Transactional
    public void deleteById(String UUID) {
        carroRepository.deleteCarroByClienteId(fromString(UUID));
        clienteRepository.deleteById(fromString(UUID));
    }


}
