package com.example.mini_sirh.repository;

import com.example.mini_sirh.entity.Collaborateur;
import com.example.mini_sirh.entity.Pointage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PointageRepository extends JpaRepository<Pointage, Long> {
    Optional<Pointage> findByCollaborateurAndDatePointage(Collaborateur collaborateur, LocalDate datePointage);
    List<Pointage> findByDatePointage(LocalDate datePointage);
}