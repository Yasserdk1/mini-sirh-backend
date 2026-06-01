package com.example.mini_sirh.repository;

import com.example.mini_sirh.entity.Collaborateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CollaborateurRepository extends JpaRepository<Collaborateur, Long> {
    Optional<Collaborateur> findByRfidCode(String rfidCode);
    Optional<Collaborateur> findByEmail(String email);
}