package com.example.mini_sirh.dto;

import com.example.mini_sirh.entity.enums.StatutCollaborateur;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class CollaborateurResponse {

    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String cin;
    private String poste;
    private LocalDate dateEmbauche;
    private String rfidCode;
    private StatutCollaborateur statut;

    private Long departementId;
    private String departementNom;
}