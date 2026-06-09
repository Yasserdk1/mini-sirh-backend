package com.example.mini_sirh.dto;

import com.example.mini_sirh.entity.enums.StatutFormation;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class FormationResponse {

    private Long id;
    private String titre;
    private String description;
    private String formateur;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private StatutFormation statut;

    private int nombreCollaborateurs;
}