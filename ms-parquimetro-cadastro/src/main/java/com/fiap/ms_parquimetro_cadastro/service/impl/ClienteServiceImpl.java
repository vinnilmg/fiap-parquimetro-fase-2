package com.fiap.ms_parquimetro_cadastro.service.impl;

import com.fiap.ms_parquimetro_cadastro.controller.mapper.ClienteMapper;
import com.fiap.ms_parquimetro_cadastro.controller.response.ClienteResponse;
import com.fiap.ms_parquimetro_cadastro.controller.resquest.ClienteRequest;
import com.fiap.ms_parquimetro_cadastro.repository.ClienteRepository;
import com.fiap.ms_parquimetro_cadastro.service.ClienteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.Objects;
import java.util.UUID;

import static java.util.UUID.fromString;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteServiceImpl(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
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
        return clienteRepository.findById(fromString(UUID))
                .map(clienteMapper::ClienteEntityToResponse)
                .orElseThrow(() -> new EntityNotFoundException(UUID));
    }

    @Override
    public ClienteResponse save(ClienteRequest cliente) {
        if (clienteRepository.existsClienteByCnh(Objects.requireNonNull(cliente.getCnh()))) {
            throw new RuntimeException("CNH já cadastrada.");
        }

        return clienteMapper.ClienteEntityToResponse(
                clienteRepository.save
                        (clienteMapper.ClienteRequestToEntity(cliente)));
    }

    @Override
    public ClienteResponse update(String UUID, ClienteRequest cliente) {
        if (!clienteRepository.existsById(fromString(UUID))) {
            throw new RuntimeException("Cliente não cadastrado.");
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
    public void deleteById(String UUID) {
        clienteRepository.deleteById(fromString(UUID));
    }




}
