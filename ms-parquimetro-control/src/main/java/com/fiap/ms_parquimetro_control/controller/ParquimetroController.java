package com.fiap.ms_parquimetro_control.controller;

import com.fiap.ms_parquimetro_control.controller.request.ParkingPerHourRequest;
import com.fiap.ms_parquimetro_control.controller.response.ParkingPerHourResponse;
import com.fiap.ms_parquimetro_control.controller.response.mapper.ParkingPerHourResponseMapper;
import com.fiap.ms_parquimetro_control.repository.entity.Estacionamento;
import com.fiap.ms_parquimetro_control.service.ParquimetroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParquimetroController {
    @Autowired
    private ParquimetroService service;

    @Autowired
    private ParkingPerHourResponseMapper parkingPerHourResponseMapper;

    @GetMapping
    public ResponseEntity<List<Estacionamento>> buscaControleEstacionamento() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<ParkingPerHourResponse> novoEstacionamentoPorHora(
            @Valid @RequestBody ParkingPerHourRequest request
    ) {
        final var parking = service.novoEstacionamentoPorHora(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(parkingPerHourResponseMapper.toParkingPerHourResponse(parking));
    }
}
