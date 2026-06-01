package com.example.mini_sirh.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartementRequest {

    @NotBlank(message = "Le nom du département est obligatoire")
    private String nom;

    private String description;
}