package com.example.mini_sirh.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PointageRequest {

    @NotBlank(message = "Le code RFID est obligatoire")
    private String rfidCode;

    private String deviceId;

    private LocalDateTime timestamp;
}