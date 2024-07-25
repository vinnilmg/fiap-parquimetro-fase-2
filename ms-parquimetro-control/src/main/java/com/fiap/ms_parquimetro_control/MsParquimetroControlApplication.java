package com.fiap.ms_parquimetro_control;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableMongoRepositories
@EnableScheduling
@EnableCaching
@EnableFeignClients
public class MsParquimetroControlApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsParquimetroControlApplication.class, args);
    }
}
