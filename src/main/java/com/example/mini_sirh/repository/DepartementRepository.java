package com.example.mini_sirh.repository;

import com.example.mini_sirh.entity.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartementRepository extends JpaRepository<Departement, Long> {
}