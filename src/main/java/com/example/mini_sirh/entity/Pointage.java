package com.example.mini_sirh.entity;

import com.example.mini_sirh.entity.enums.StatutPointage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "pointages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pointage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate datePointage;

    private LocalTime heureEntree;

    private LocalTime heureSortie;

    @Enumerated(EnumType.STRING)
    private StatutPointage statut;

    private String deviceId;

    @ManyToOne
    @JoinColumn(name = "collaborateur_id", nullable = false)
    @JsonIgnoreProperties({"pointages", "conges", "formations"})
    private Collaborateur collaborateur;
}