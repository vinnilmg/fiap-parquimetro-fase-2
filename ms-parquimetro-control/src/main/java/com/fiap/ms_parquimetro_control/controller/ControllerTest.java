package com.fiap.ms_parquimetro_control.controller;

import com.fiap.ms_parquimetro_control.repository.MongoRepositoryTest;
import com.fiap.ms_parquimetro_control.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class ControllerTest {

    @Autowired
    private MongoRepositoryTest repositoryTest;

    @GetMapping
    public String test() {
        return "Hello world";
    }

    @GetMapping(path = "/users")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok().body(repositoryTest.findAll());
    }

    @PostMapping(path = "/users")
    public ResponseEntity<User> create() {
        final var user = new User();
        user.setId(1L);
        user.setName("Vinicius");
        return ResponseEntity.ok().body(repositoryTest.insert(user));
    }
}
