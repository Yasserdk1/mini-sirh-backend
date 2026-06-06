package com.example.mini_sirh.dto;

import com.example.mini_sirh.entity.enums.StatutFormation;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FormationRequest {

    @NotBlank(message = "Le titre de la formation est obligatoire")
    private String titre;

    private String description;

    private String formateur;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    private StatutFormation statut;
}