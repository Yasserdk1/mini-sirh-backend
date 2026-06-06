package com.example.mini_sirh.service;

import com.example.mini_sirh.dto.CongeRequest;
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

    public Conge create(CongeRequest request) {
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

        return congeRepository.save(conge);
    }

    public List<Conge> findAll() {
        return congeRepository.findAll();
    }

    public Conge findById(Long id) {
        return congeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Congé introuvable avec l'id : " + id));
    }

    public Conge update(Long id, CongeRequest request) {
        Conge conge = findById(id);

        Collaborateur collaborateur = collaborateurRepository.findById(request.getCollaborateurId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Collaborateur introuvable avec l'id : " + request.getCollaborateurId()
                ));

        conge.setDateDebut(request.getDateDebut());
        conge.setDateFin(request.getDateFin());
        conge.setMotif(request.getMotif());
        conge.setCollaborateur(collaborateur);

        return congeRepository.save(conge);
    }

    public Conge accepter(Long id) {
        Conge conge = findById(id);
        conge.setStatut(StatutConge.ACCEPTE);
        return congeRepository.save(conge);
    }

    public Conge refuser(Long id) {
        Conge conge = findById(id);
        conge.setStatut(StatutConge.REFUSE);
        return congeRepository.save(conge);
    }

    public void delete(Long id) {
        Conge conge = findById(id);
        congeRepository.delete(conge);
    }
}