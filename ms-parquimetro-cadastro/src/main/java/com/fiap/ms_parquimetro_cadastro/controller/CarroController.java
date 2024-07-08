package com.fiap.ms_parquimetro_cadastro.controller;

import com.fiap.ms_parquimetro_cadastro.controller.mapper.CarroMapper;
import com.fiap.ms_parquimetro_cadastro.controller.response.CarroResponse;
import com.fiap.ms_parquimetro_cadastro.controller.resquest.CarroRequest;
import com.fiap.ms_parquimetro_cadastro.service.CarroService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/carro")
public class CarroController {
    private final CarroService carroService;
    private final CarroMapper carroMapper;
    public CarroController(CarroService carroService, CarroMapper carroMapper) {
        this.carroService = carroService;
        this.carroMapper = carroMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarroResponse> getCarroById(@PathVariable String id) {
        CarroResponse carro = carroService.findById(id);
        return ResponseEntity.ok(carro);
    }

    @GetMapping
    public ResponseEntity<List<CarroResponse>> getAllCarros() {
        List<CarroResponse> carros = carroService.findAll();
        return ResponseEntity.ok(carros);
    }

    @PostMapping
    public ResponseEntity<CarroResponse> createCarro(@Valid @RequestBody CarroRequest carroRequest) {
        CarroResponse carro = carroService.save(carroRequest);
        return ResponseEntity.status(201).body(carro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarroResponse> updateCarro(@PathVariable String id,@Valid @RequestBody CarroRequest carroRequest) {
        CarroResponse updatedCarro = carroService.update(id, carroRequest);
        return ResponseEntity.ok(updatedCarro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarro(@PathVariable String id) {
        carroService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
