package com.fiap.ms_parquimetro_control.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParkingPerHourRequest extends DefaultRequest {
    private String observacoes;
}