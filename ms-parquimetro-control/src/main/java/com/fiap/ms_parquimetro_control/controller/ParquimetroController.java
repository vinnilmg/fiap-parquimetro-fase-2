package com.fiap.ms_parquimetro_control.controller;

import com.fiap.ms_parquimetro_control.controller.request.*;
import com.fiap.ms_parquimetro_control.controller.response.*;
import com.fiap.ms_parquimetro_control.controller.response.mapper.*;
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
    @Autowired
    private PaymentReceiptResponseMapper paymentReceiptResponseMapper;
    @Autowired
    private ParkingResponseMapper parkingResponseMapper;
    @Autowired
    private ParkingSaidaVariavelResponseMapper parkingSaidaVariavelResponseMapper;
    @Autowired
    private ParkingFixResponseMapper parkingFixResponseMapper;
    @Autowired
    private FixedParkingExitResponseMapper fixedParkingExitResponseMapper;

    @GetMapping
    public ResponseEntity<List<ParkingResponse>> buscaControleEstacionamento() {
        return ResponseEntity.ok(
                service.findAll()
                        .stream()
                        .map(parkingResponseMapper::toParkingResponse)
                        .toList()
        );
    }

    @PostMapping("/variavel")
    public ResponseEntity<ParkingPerHourResponse> novoEstacionamentoPorHora(
            @Valid @RequestBody final ParkingPerHourRequest request
    ) {
        final var parking = service.novoEstacionamentoPorHora(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(parkingPerHourResponseMapper.toParkingPerHourResponse(parking));
    }

    @PostMapping("/fixo")
    public ResponseEntity<ParkingFixResponse> novoEstacionamentoFixo(
            @Valid @RequestBody ParkingFixRequest request
    ) {
        final var parking = service.novoEstacionamentoFixo(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(parkingFixResponseMapper.toParkingFixResponse(parking));
    }

    @PutMapping("/variavel")
    public ResponseEntity<ParkingSaidaVariavelResponse> registrarSaidaEstacionamentoPorHora(
            @Valid @RequestBody ParkingSaidaVariavelRequest request
    ) {
        final var parking = service.registrarSaidaEstacionamentoVariavel(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(parkingSaidaVariavelResponseMapper.toParkingSaidaVariavelResponse(parking));
    }

    @PutMapping("/fixo")
    public ResponseEntity<FixedParkingExitResponse> registrarSaidaEstacionamentoFixo(
            @Valid @RequestBody final FixedParkingExitRequest request
    ) {
        final var parking = service.registrarSaidaEstacionamentoFixo(request);
        return ResponseEntity.ok().body(fixedParkingExitResponseMapper.toFixedParkingExitResponse(parking));
    }

    @PutMapping("/finalizacao")
    public ResponseEntity<PaymentReceiptResponse> finalizaEstacionamento(
            @Valid @RequestBody final FinalizacaoRequest request
    ) {
        final var parking = service.finalizaEstacionamento(request);
        return ResponseEntity.ok().body(paymentReceiptResponseMapper.toPaymentReceiptResponse(parking));
    }
}
