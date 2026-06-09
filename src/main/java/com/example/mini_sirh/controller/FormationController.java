package com.example.mini_sirh.controller;

import com.example.mini_sirh.dto.FormationRequest;
import com.example.mini_sirh.dto.FormationResponse;
import com.example.mini_sirh.service.FormationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/formations")
@RequiredArgsConstructor
public class FormationController {

    private final FormationService formationService;

    @PostMapping
    public FormationResponse create(@Valid @RequestBody FormationRequest request) {
        return formationService.create(request);
    }

    @GetMapping
    public List<FormationResponse> findAll() {
        return formationService.findAll();
    }

    @GetMapping("/{id}")
    public FormationResponse findById(@PathVariable Long id) {
        return formationService.findById(id);
    }

    @PutMapping("/{id}")
    public FormationResponse update(@PathVariable Long id, @Valid @RequestBody FormationRequest request) {
        return formationService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        formationService.delete(id);
    }

    @PostMapping("/{formationId}/collaborateurs/{collaborateurId}")
    public FormationResponse assignCollaborateur(
            @PathVariable Long formationId,
            @PathVariable Long collaborateurId
    ) {
        return formationService.assignCollaborateur(formationId, collaborateurId);
    }
}