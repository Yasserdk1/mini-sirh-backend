package com.example.mini_sirh.dto;

import com.example.mini_sirh.entity.enums.StatutPointage;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
public class PointageResponse {

    private Long id;
    private LocalDate datePointage;
    private LocalTime heureEntree;
    private LocalTime heureSortie;
    private StatutPointage statut;
    private String deviceId;

    private Long collaborateurId;
    private String collaborateurNomComplet;
    private String rfidCode;
}