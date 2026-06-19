package com.example.mini_sirh.repository;

import com.example.mini_sirh.entity.Projet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetRepository extends JpaRepository<Projet, Long> {
}