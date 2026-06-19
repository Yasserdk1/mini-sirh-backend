package com.example.mini_sirh.dto;

import com.example.mini_sirh.entity.enums.StatutProjet;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProjetRequest {

    @NotBlank(message = "Le nom du projet est obligatoire")
    private String nom;

    private String description;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private StatutProjet statut;
}