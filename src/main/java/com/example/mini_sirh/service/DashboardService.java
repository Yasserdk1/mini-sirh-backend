package com.example.mini_sirh.service;

import com.example.mini_sirh.dto.DashboardResponse;
import com.example.mini_sirh.entity.enums.StatutConge;
import com.example.mini_sirh.entity.enums.StatutPointage;
import com.example.mini_sirh.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final CollaborateurRepository collaborateurRepository;
    private final DepartementRepository departementRepository;
    private final FormationRepository formationRepository;
    private final CongeRepository congeRepository;
    private final PointageRepository pointageRepository;

    public DashboardResponse getStats() {
        LocalDate today = LocalDate.now();

        return DashboardResponse.builder()
                .totalCollaborateurs(collaborateurRepository.count())
                .totalDepartements(departementRepository.count())
                .totalFormations(formationRepository.count())
                .totalConges(congeRepository.count())
                .pointagesAujourdhui(pointageRepository.countByDatePointage(today))
                .retardsAujourdhui(pointageRepository.countByDatePointageAndStatut(today, StatutPointage.RETARD))
                .congesEnAttente(congeRepository.countByStatut(StatutConge.EN_ATTENTE))
                .build();
    }
}