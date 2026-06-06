package com.example.mini_sirh.repository;

import com.example.mini_sirh.entity.Conge;
import com.example.mini_sirh.entity.enums.StatutConge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CongeRepository extends JpaRepository<Conge, Long> {

    long countByStatut(StatutConge statut);
}