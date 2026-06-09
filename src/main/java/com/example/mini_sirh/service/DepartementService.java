package com.example.mini_sirh.service;

import com.example.mini_sirh.dto.DepartementRequest;
import com.example.mini_sirh.dto.DepartementResponse;
import com.example.mini_sirh.entity.Departement;
import com.example.mini_sirh.exception.ResourceNotFoundException;
import com.example.mini_sirh.repository.DepartementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartementService {

    private final DepartementRepository departementRepository;

    public DepartementResponse create(DepartementRequest request) {
        Departement departement = Departement.builder()
                .nom(request.getNom())
                .description(request.getDescription())
                .build();

        return mapToResponse(departementRepository.save(departement));
    }

    public List<DepartementResponse> findAll() {
        return departementRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public DepartementResponse findById(Long id) {
        return mapToResponse(getEntityById(id));
    }

    public DepartementResponse update(Long id, DepartementRequest request) {
        Departement departement = getEntityById(id);

        departement.setNom(request.getNom());
        departement.setDescription(request.getDescription());

        return mapToResponse(departementRepository.save(departement));
    }

    public void delete(Long id) {
        Departement departement = getEntityById(id);
        departementRepository.delete(departement);
    }

    public Departement getEntityById(Long id) {
        return departementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Département introuvable avec l'id : " + id));
    }

    private DepartementResponse mapToResponse(Departement departement) {
        return DepartementResponse.builder()
                .id(departement.getId())
                .nom(departement.getNom())
                .description(departement.getDescription())
                .nombreCollaborateurs(
                        departement.getCollaborateurs() != null ? departement.getCollaborateurs().size() : 0
                )
                .build();
    }
}