package com.example.mini_sirh.service;

import com.example.mini_sirh.dto.CongeRequest;
import com.example.mini_sirh.dto.CongeResponse;
import com.example.mini_sirh.entity.Collaborateur;
import com.example.mini_sirh.entity.Conge;
import com.example.mini_sirh.entity.enums.StatutConge;
import com.example.mini_sirh.exception.ResourceNotFoundException;
import com.example.mini_sirh.repository.CollaborateurRepository;
import com.example.mini_sirh.repository.CongeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CongeService {

    private final CongeRepository congeRepository;
    private final CollaborateurRepository collaborateurRepository;

    public CongeResponse create(CongeRequest request) {
        Collaborateur collaborateur = collaborateurRepository.findById(request.getCollaborateurId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Collaborateur introuvable avec l'id : " + request.getCollaborateurId()
                ));

        Conge conge = Conge.builder()
                .dateDebut(request.getDateDebut())
                .dateFin(request.getDateFin())
                .motif(request.getMotif())
                .statut(StatutConge.EN_ATTENTE)
                .collaborateur(collaborateur)
                .build();

        return mapToResponse(congeRepository.save(conge));
    }

    public List<CongeResponse> findAll() {
        return congeRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public CongeResponse findById(Long id) {
        return mapToResponse(getEntityById(id));
    }

    public CongeResponse update(Long id, CongeRequest request) {
        Conge conge = getEntityById(id);

        Collaborateur collaborateur = collaborateurRepository.findById(request.getCollaborateurId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Collaborateur introuvable avec l'id : " + request.getCollaborateurId()
                ));

        conge.setDateDebut(request.getDateDebut());
        conge.setDateFin(request.getDateFin());
        conge.setMotif(request.getMotif());
        conge.setCollaborateur(collaborateur);

        return mapToResponse(congeRepository.save(conge));
    }

    public CongeResponse accepter(Long id) {
        Conge conge = getEntityById(id);
        conge.setStatut(StatutConge.ACCEPTE);
        return mapToResponse(congeRepository.save(conge));
    }

    public CongeResponse refuser(Long id) {
        Conge conge = getEntityById(id);
        conge.setStatut(StatutConge.REFUSE);
        return mapToResponse(congeRepository.save(conge));
    }

    public void delete(Long id) {
        Conge conge = getEntityById(id);
        congeRepository.delete(conge);
    }

    public Conge getEntityById(Long id) {
        return congeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Congé introuvable avec l'id : " + id));
    }

    private CongeResponse mapToResponse(Conge conge) {
        Collaborateur collaborateur = conge.getCollaborateur();

        return CongeResponse.builder()
                .id(conge.getId())
                .dateDebut(conge.getDateDebut())
                .dateFin(conge.getDateFin())
                .motif(conge.getMotif())
                .statut(conge.getStatut())
                .collaborateurId(collaborateur.getId())
                .collaborateurNomComplet(collaborateur.getNom() + " " + collaborateur.getPrenom())
                .build();
    }
}