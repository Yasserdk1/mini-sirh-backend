package com.example.mini_sirh.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CongeRequest {

    @NotNull(message = "La date de début est obligatoire")
    private LocalDate dateDebut;

    @NotNull(message = "La date de fin est obligatoire")
    private LocalDate dateFin;

    private String motif;

    @NotNull(message = "L'identifiant du collaborateur est obligatoire")
    private Long collaborateurId;
}