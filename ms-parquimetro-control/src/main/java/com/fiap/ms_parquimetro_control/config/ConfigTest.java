package com.fiap.ms_parquimetro_control.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ConfigTest {

    @Value("${spring.data.mongodb.host}")
    private String mongoHost;

    @Bean
    public String init() {
        log.info("MONGO HOST: {}", mongoHost);
        return mongoHost;
    }
}
