package com.example.mini_sirh.dto;

import com.example.mini_sirh.entity.enums.StatutConge;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class CongeResponse {

    private Long id;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String motif;
    private StatutConge statut;

    private Long collaborateurId;
    private String collaborateurNomComplet;
}