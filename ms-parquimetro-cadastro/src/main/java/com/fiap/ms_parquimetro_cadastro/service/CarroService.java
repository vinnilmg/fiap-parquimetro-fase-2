package com.fiap.ms_parquimetro_cadastro.service;

import com.fiap.ms_parquimetro_cadastro.controller.response.CarroResponse;
import com.fiap.ms_parquimetro_cadastro.controller.response.MessageResponse;
import com.fiap.ms_parquimetro_cadastro.controller.resquest.CarroRequest;
import com.fiap.ms_parquimetro_cadastro.controller.resquest.CarroUpdateRequest;
import jakarta.transaction.Transactional;

import java.util.List;

public interface CarroService {

    public CarroResponse findById(String UUID);

    public CarroResponse findCarroByPlaca(String placa);

    public List<CarroResponse> findAll();

    public CarroResponse save(CarroRequest carro);

    public CarroResponse update(String UUID, CarroUpdateRequest carro);

    public MessageResponse deleteById(String UUID);

}
