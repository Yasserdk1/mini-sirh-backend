package com.example.mini_sirh.dto;

import com.example.mini_sirh.entity.enums.StatutCollaborateur;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CollaborateurRequest {

    @NotBlank
    private String nom;

    @NotBlank
    private String prenom;

    @Email
    private String email;

    private String telephone;

    @NotBlank
    private String cin;

    private String poste;

    private LocalDate dateEmbauche;

    @NotBlank
    private String rfidCode;

    private StatutCollaborateur statut;

    private Long departementId;
}