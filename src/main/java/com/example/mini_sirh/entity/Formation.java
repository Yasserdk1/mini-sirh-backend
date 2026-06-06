package com.example.mini_sirh.entity;

import com.example.mini_sirh.entity.enums.StatutFormation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "formations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String formateur;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    @Enumerated(EnumType.STRING)
    private StatutFormation statut;

    @ManyToMany(mappedBy = "formations")
    @JsonIgnore
    private List<Collaborateur> collaborateurs = new ArrayList<>();
}