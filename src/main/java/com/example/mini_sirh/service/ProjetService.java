package com.example.mini_sirh.service;

import com.example.mini_sirh.dto.ProjetRequest;
import com.example.mini_sirh.dto.ProjetResponse;
import com.example.mini_sirh.entity.Collaborateur;
import com.example.mini_sirh.entity.Projet;
import com.example.mini_sirh.exception.ResourceNotFoundException;
import com.example.mini_sirh.repository.CollaborateurRepository;
import com.example.mini_sirh.repository.ProjetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjetService {

    private final ProjetRepository projetRepository;
    private final CollaborateurRepository collaborateurRepository;

    public ProjetResponse create(ProjetRequest request) {
        Projet projet = Projet.builder()
                .nom(request.getNom())
                .description(request.getDescription())
                .dateDebut(request.getDateDebut())
                .dateFin(request.getDateFin())
                .statut(request.getStatut())
                .build();

        return mapToResponse(projetRepository.save(projet));
    }

    public List<ProjetResponse> findAll() {
        return projetRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ProjetResponse findById(Long id) {
        return mapToResponse(getEntityById(id));
    }

    public ProjetResponse update(Long id, ProjetRequest request) {
        Projet projet = getEntityById(id);

        projet.setNom(request.getNom());
        projet.setDescription(request.getDescription());
        projet.setDateDebut(request.getDateDebut());
        projet.setDateFin(request.getDateFin());
        projet.setStatut(request.getStatut());

        return mapToResponse(projetRepository.save(projet));
    }

    public void delete(Long id) {
        Projet projet = getEntityById(id);
        projetRepository.delete(projet);
    }

    public ProjetResponse assignCollaborateur(Long projetId, Long collaborateurId) {
        Projet projet = getEntityById(projetId);

        Collaborateur collaborateur = collaborateurRepository.findById(collaborateurId)
                .orElseThrow(() -> new ResourceNotFoundException("Collaborateur introuvable avec l'id : " + collaborateurId));

        if (!collaborateur.getProjets().contains(projet)) {
            collaborateur.getProjets().add(projet);
            collaborateurRepository.save(collaborateur);
        }

        return mapToResponse(getEntityById(projetId));
    }

    private Projet getEntityById(Long id) {
        return projetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Projet introuvable avec l'id : " + id));
    }

    private ProjetResponse mapToResponse(Projet projet) {
        return ProjetResponse.builder()
                .id(projet.getId())
                .nom(projet.getNom())
                .description(projet.getDescription())
                .dateDebut(projet.getDateDebut())
                .dateFin(projet.getDateFin())
                .statut(projet.getStatut())
                .nombreCollaborateurs(
                        projet.getCollaborateurs() != null ? projet.getCollaborateurs().size() : 0
                )
                .build();
    }
}