package com.example.mini_sirh.service;

import com.example.mini_sirh.dto.CollaborateurRequest;
import com.example.mini_sirh.dto.CollaborateurResponse;
import com.example.mini_sirh.entity.Collaborateur;
import com.example.mini_sirh.entity.Departement;
import com.example.mini_sirh.exception.ResourceNotFoundException;
import com.example.mini_sirh.repository.CollaborateurRepository;
import com.example.mini_sirh.repository.DepartementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CollaborateurService {

    private final CollaborateurRepository collaborateurRepository;
    private final DepartementRepository departementRepository;

    public CollaborateurResponse create(CollaborateurRequest request) {
        Departement departement = departementRepository.findById(request.getDepartementId())
                .orElseThrow(() -> new ResourceNotFoundException("Département introuvable avec l'id : " + request.getDepartementId()));

        Collaborateur collaborateur = Collaborateur.builder()
                .nom(request.getNom())
                .prenom(request.getPrenom())
                .email(request.getEmail())
                .telephone(request.getTelephone())
                .cin(request.getCin())
                .poste(request.getPoste())
                .dateEmbauche(request.getDateEmbauche())
                .rfidCode(request.getRfidCode())
                .statut(request.getStatut())
                .departement(departement)
                .build();

        return mapToResponse(collaborateurRepository.save(collaborateur));
    }

    public List<CollaborateurResponse> findAll() {
        return collaborateurRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public CollaborateurResponse findById(Long id) {
        return mapToResponse(getEntityById(id));
    }

    public CollaborateurResponse update(Long id, CollaborateurRequest request) {
        Collaborateur collaborateur = getEntityById(id);

        Departement departement = departementRepository.findById(request.getDepartementId())
                .orElseThrow(() -> new ResourceNotFoundException("Département introuvable avec l'id : " + request.getDepartementId()));

        collaborateur.setNom(request.getNom());
        collaborateur.setPrenom(request.getPrenom());
        collaborateur.setEmail(request.getEmail());
        collaborateur.setTelephone(request.getTelephone());
        collaborateur.setCin(request.getCin());
        collaborateur.setPoste(request.getPoste());
        collaborateur.setDateEmbauche(request.getDateEmbauche());
        collaborateur.setRfidCode(request.getRfidCode());
        collaborateur.setStatut(request.getStatut());
        collaborateur.setDepartement(departement);

        return mapToResponse(collaborateurRepository.save(collaborateur));
    }

    public void delete(Long id) {
        Collaborateur collaborateur = getEntityById(id);
        collaborateurRepository.delete(collaborateur);
    }

    public Collaborateur getEntityById(Long id) {
        return collaborateurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Collaborateur introuvable avec l'id : " + id));
    }

    private CollaborateurResponse mapToResponse(Collaborateur collaborateur) {
        return CollaborateurResponse.builder()
                .id(collaborateur.getId())
                .nom(collaborateur.getNom())
                .prenom(collaborateur.getPrenom())
                .email(collaborateur.getEmail())
                .telephone(collaborateur.getTelephone())
                .cin(collaborateur.getCin())
                .poste(collaborateur.getPoste())
                .dateEmbauche(collaborateur.getDateEmbauche())
                .rfidCode(collaborateur.getRfidCode())
                .statut(collaborateur.getStatut())
                .departementId(collaborateur.getDepartement() != null ? collaborateur.getDepartement().getId() : null)
                .departementNom(collaborateur.getDepartement() != null ? collaborateur.getDepartement().getNom() : null)
                .build();
    }
}