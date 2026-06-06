package com.example.mini_sirh.service;

import com.example.mini_sirh.dto.PointageRequest;
import com.example.mini_sirh.entity.Collaborateur;
import com.example.mini_sirh.entity.Pointage;
import com.example.mini_sirh.entity.enums.StatutPointage;
import com.example.mini_sirh.exception.DuplicatePointageException;
import com.example.mini_sirh.exception.ResourceNotFoundException;
import com.example.mini_sirh.repository.CollaborateurRepository;
import com.example.mini_sirh.repository.PointageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PointageService {

    private final PointageRepository pointageRepository;
    private final CollaborateurRepository collaborateurRepository;

    private static final LocalTime HEURE_LIMITE = LocalTime.of(9, 0);

    public Pointage enregistrerPointage(PointageRequest request) {
        Collaborateur collaborateur = collaborateurRepository.findByRfidCode(request.getRfidCode())
                .orElseThrow(() -> new ResourceNotFoundException("Aucun collaborateur trouvé avec le code RFID : " + request.getRfidCode()));

        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();

        Pointage pointage = pointageRepository
                .findByCollaborateurAndDatePointage(collaborateur, today)
                .orElse(null);

        if (pointage == null) {
            StatutPointage statut = now.isAfter(HEURE_LIMITE)
                    ? StatutPointage.RETARD
                    : StatutPointage.PRESENT;

            Pointage newPointage = Pointage.builder()
                    .collaborateur(collaborateur)
                    .datePointage(today)
                    .heureEntree(now)
                    .statut(statut)
                    .deviceId(request.getDeviceId())
                    .build();

            return pointageRepository.save(newPointage);
        }

        if (pointage.getHeureSortie() == null) {
            pointage.setHeureSortie(now);
            pointage.setStatut(StatutPointage.SORTI);
            return pointageRepository.save(pointage);
        }

        throw new DuplicatePointageException("Pointage déjà complet pour aujourd'hui");
    }

    public List<Pointage> getPointagesDuJour() {
        return pointageRepository.findByDatePointage(LocalDate.now());
    }

    public List<Pointage> findAll() {
        return pointageRepository.findAll();
    }
}