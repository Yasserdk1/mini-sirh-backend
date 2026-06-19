package com.example.mini_sirh.dto;

import com.example.mini_sirh.entity.enums.StatutProjet;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class ProjetResponse {

    private Long id;
    private String nom;
    private String description;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private StatutProjet statut;
    private int nombreCollaborateurs;
}