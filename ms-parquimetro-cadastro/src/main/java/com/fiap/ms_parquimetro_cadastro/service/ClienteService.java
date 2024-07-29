package com.fiap.ms_parquimetro_cadastro.service;

import com.fiap.ms_parquimetro_cadastro.controller.response.ClienteResponse;
import com.fiap.ms_parquimetro_cadastro.controller.response.MessageResponse;
import com.fiap.ms_parquimetro_cadastro.controller.resquest.ClienteRequest;

import java.util.List;

public interface ClienteService {

    public List<ClienteResponse> findAll();

    public ClienteResponse findById(String UUID);

    public ClienteResponse findClienteByCnh(String cnh);

    public ClienteResponse save(ClienteRequest cliente);

    public ClienteResponse update(String UUID, ClienteRequest cliente);

    public MessageResponse deleteById(String UUID);
}
