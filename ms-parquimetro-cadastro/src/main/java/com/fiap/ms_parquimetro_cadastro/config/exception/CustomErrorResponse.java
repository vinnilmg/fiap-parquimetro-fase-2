package com.fiap.ms_parquimetro_cadastro.config.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomErrorResponse {
    private String errorCode;
    private String message;
    private String timestamp;


    public CustomErrorResponse(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }
}
