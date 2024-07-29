package com.fiap.ms_parquimetro_cadastro.controller;

import com.fiap.ms_parquimetro_cadastro.controller.response.ClienteResponse;
import com.fiap.ms_parquimetro_cadastro.controller.response.MessageResponse;
import com.fiap.ms_parquimetro_cadastro.controller.resquest.ClienteRequest;
import com.fiap.ms_parquimetro_cadastro.controller.resquest.ClienteUpdateRequest;
import com.fiap.ms_parquimetro_cadastro.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cliente")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> getAllClientes() {
        List<ClienteResponse> clientes = clienteService.findAll();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> getClienteById(@PathVariable String id) {
        ClienteResponse cliente = clienteService.findById(id);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/cnh/{cnh}")
    public ResponseEntity<ClienteResponse> getClienteByCnh(@PathVariable String cnh) {
        ClienteResponse cliente = clienteService.findClienteByCnh(cnh);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> createCliente(@Valid @RequestBody ClienteRequest clienteRequest) {
        ClienteResponse cliente = clienteService.save(clienteRequest);
        return ResponseEntity.status(201).body(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> updateCliente(@PathVariable String id, @Valid @RequestBody ClienteUpdateRequest clienteUpdateRequest) {
        ClienteResponse updatedCliente = clienteService.update(id, clienteUpdateRequest);
        return ResponseEntity.ok(updatedCliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteCliente(@PathVariable String id) {
        MessageResponse messageResponse = clienteService.deleteById(id);
        return ResponseEntity.ok(messageResponse);
    }
}
