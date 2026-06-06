package com.example.mini_sirh.service;

import com.example.mini_sirh.dto.FormationRequest;
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

    public Formation create(FormationRequest request) {
        Formation formation = Formation.builder()
                .titre(request.getTitre())
                .description(request.getDescription())
                .formateur(request.getFormateur())
                .dateDebut(request.getDateDebut())
                .dateFin(request.getDateFin())
                .statut(request.getStatut())
                .build();

        return formationRepository.save(formation);
    }

    public List<Formation> findAll() {
        return formationRepository.findAll();
    }

    public Formation findById(Long id) {
        return formationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Formation introuvable avec l'id : " + id));
    }

    public Formation update(Long id, FormationRequest request) {
        Formation formation = findById(id);

        formation.setTitre(request.getTitre());
        formation.setDescription(request.getDescription());
        formation.setFormateur(request.getFormateur());
        formation.setDateDebut(request.getDateDebut());
        formation.setDateFin(request.getDateFin());
        formation.setStatut(request.getStatut());

        return formationRepository.save(formation);
    }

    public void delete(Long id) {
        Formation formation = findById(id);
        formationRepository.delete(formation);
    }

    public Formation assignCollaborateur(Long formationId, Long collaborateurId) {
        Formation formation = findById(formationId);

        Collaborateur collaborateur = collaborateurRepository.findById(collaborateurId)
                .orElseThrow(() -> new ResourceNotFoundException("Collaborateur introuvable avec l'id : " + collaborateurId));

        if (!collaborateur.getFormations().contains(formation)) {
            collaborateur.getFormations().add(formation);
            collaborateurRepository.save(collaborateur);
        }

        return formationRepository.findById(formationId)
                .orElseThrow(() -> new ResourceNotFoundException("Formation introuvable avec l'id : " + formationId));
    }
}