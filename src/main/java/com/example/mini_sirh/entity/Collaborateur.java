package com.example.mini_sirh.entity;

import com.example.mini_sirh.entity.enums.StatutCollaborateur;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "collaborateurs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Collaborateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;

    @Column(unique = true)
    private String email;

    private String telephone;

    @Column(unique = true)
    private String cin;

    private String poste;
    private LocalDate dateEmbauche;

    @Column(unique = true, nullable = false)
    private String rfidCode;

    @Enumerated(EnumType.STRING)
    private StatutCollaborateur statut;

    @ManyToOne
    @JoinColumn(name = "departement_id")
    private Departement departement;

    @OneToMany(mappedBy = "collaborateur")
    @JsonIgnore
    private List<Pointage> pointages = new ArrayList<>();

    @OneToMany(mappedBy = "collaborateur")
    @JsonIgnore
    private List<Conge> conges = new ArrayList<>();

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "collaborateur_formation",
            joinColumns = @JoinColumn(name = "collaborateur_id"),
            inverseJoinColumns = @JoinColumn(name = "formation_id")
    )
    private List<Formation> formations = new ArrayList<>();
    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "collaborateur_projet",
            joinColumns = @JoinColumn(name = "collaborateur_id"),
            inverseJoinColumns = @JoinColumn(name = "projet_id")
    )
    private List<Projet> projets = new ArrayList<>();
}