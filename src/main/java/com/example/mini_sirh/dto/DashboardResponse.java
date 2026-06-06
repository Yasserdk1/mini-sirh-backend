package com.example.mini_sirh.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DashboardResponse {

    private long totalCollaborateurs;
    private long totalDepartements;
    private long totalFormations;
    private long totalConges;

    private long pointagesAujourdhui;
    private long retardsAujourdhui;
    private long congesEnAttente;
}