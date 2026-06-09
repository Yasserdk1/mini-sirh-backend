package com.example.mini_sirh.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DepartementResponse {

    private Long id;
    private String nom;
    private String description;
    private int nombreCollaborateurs;
}