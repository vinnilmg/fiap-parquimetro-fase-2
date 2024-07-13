package com.fiap.ms_parquimetro_cadastro.service;

import com.fiap.ms_parquimetro_cadastro.controller.response.CarroResponse;
import com.fiap.ms_parquimetro_cadastro.controller.resquest.CarroRequest;

import java.util.List;

public interface CarroService {

    public CarroResponse findById(String UUID);

    public List<CarroResponse> findAll();

    public CarroResponse save(CarroRequest carro);

    public CarroResponse update(String UUID,CarroRequest carro);

    public void deleteById(String UUID);

}
