package com.example.mini_sirh.service;

import com.example.mini_sirh.dto.CollaborateurRequest;
import com.example.mini_sirh.entity.Collaborateur;
import com.example.mini_sirh.entity.Departement;
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

    public Collaborateur create(CollaborateurRequest request) {

        Departement departement = departementRepository.findById(request.getDepartementId())
                .orElseThrow(() -> new RuntimeException("Département introuvable"));

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

        return collaborateurRepository.save(collaborateur);
    }

    public List<Collaborateur> findAll() {
        return collaborateurRepository.findAll();
    }

    public Collaborateur findById(Long id) {
        return collaborateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Collaborateur introuvable"));
    }

    public Collaborateur update(Long id, CollaborateurRequest request) {

        Collaborateur collaborateur = findById(id);

        Departement departement = departementRepository.findById(request.getDepartementId())
                .orElseThrow(() -> new RuntimeException("Département introuvable"));

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

        return collaborateurRepository.save(collaborateur);
    }

    public void delete(Long id) {
        Collaborateur collaborateur = findById(id);
        collaborateurRepository.delete(collaborateur);
    }
}