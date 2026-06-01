package com.example.mini_sirh.service;

import com.example.mini_sirh.dto.DepartementRequest;
import com.example.mini_sirh.entity.Departement;
import com.example.mini_sirh.repository.DepartementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartementService {

    private final DepartementRepository departementRepository;

    public Departement create(DepartementRequest request) {
        Departement departement = Departement.builder()
                .nom(request.getNom())
                .description(request.getDescription())
                .build();

        return departementRepository.save(departement);
    }

    public List<Departement> findAll() {
        return departementRepository.findAll();
    }

    public Departement findById(Long id) {
        return departementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Département introuvable"));
    }

    public Departement update(Long id, DepartementRequest request) {
        Departement departement = findById(id);

        departement.setNom(request.getNom());
        departement.setDescription(request.getDescription());

        return departementRepository.save(departement);
    }

    public void delete(Long id) {
        Departement departement = findById(id);
        departementRepository.delete(departement);
    }
}