package com.example.mini_sirh.service;

import com.example.mini_sirh.dto.FormationRequest;
import com.example.mini_sirh.dto.FormationResponse;
import com.example.mini_sirh.entity.Collaborateur;
import com.example.mini_sirh.entity.Formation;
import com.example.mini_sirh.exception.ResourceNotFoundException;
import com.example.mini_sirh.repository.CollaborateurRepository;
import com.example.mini_sirh.repository.FormationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FormationService {

    private final FormationRepository formationRepository;
    private final CollaborateurRepository collaborateurRepository;

    public FormationResponse create(FormationRequest request) {
        Formation formation = Formation.builder()
                .titre(request.getTitre())
                .description(request.getDescription())
                .formateur(request.getFormateur())
                .dateDebut(request.getDateDebut())
                .dateFin(request.getDateFin())
                .statut(request.getStatut())
                .build();

        return mapToResponse(formationRepository.save(formation));
    }

    public List<FormationResponse> findAll() {
        return formationRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public FormationResponse findById(Long id) {
        return mapToResponse(getEntityById(id));
    }

    public FormationResponse update(Long id, FormationRequest request) {
        Formation formation = getEntityById(id);

        formation.setTitre(request.getTitre());
        formation.setDescription(request.getDescription());
        formation.setFormateur(request.getFormateur());
        formation.setDateDebut(request.getDateDebut());
        formation.setDateFin(request.getDateFin());
        formation.setStatut(request.getStatut());

        return mapToResponse(formationRepository.save(formation));
    }

    public void delete(Long id) {
        Formation formation = getEntityById(id);
        formationRepository.delete(formation);
    }

    public FormationResponse assignCollaborateur(Long formationId, Long collaborateurId) {
        Formation formation = getEntityById(formationId);

        Collaborateur collaborateur = collaborateurRepository.findById(collaborateurId)
                .orElseThrow(() -> new ResourceNotFoundException("Collaborateur introuvable avec l'id : " + collaborateurId));

        if (!collaborateur.getFormations().contains(formation)) {
            collaborateur.getFormations().add(formation);
            collaborateurRepository.save(collaborateur);
        }

        return mapToResponse(getEntityById(formationId));
    }

    public Formation getEntityById(Long id) {
        return formationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Formation introuvable avec l'id : " + id));
    }

    private FormationResponse mapToResponse(Formation formation) {
        return FormationResponse.builder()
                .id(formation.getId())
                .titre(formation.getTitre())
                .description(formation.getDescription())
                .formateur(formation.getFormateur())
                .dateDebut(formation.getDateDebut())
                .dateFin(formation.getDateFin())
                .statut(formation.getStatut())
                .nombreCollaborateurs(
                        formation.getCollaborateurs() != null ? formation.getCollaborateurs().size() : 0
                )
                .build();
    }
}