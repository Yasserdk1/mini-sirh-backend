package com.example.mini_sirh.controller;

import com.example.mini_sirh.dto.ProjetRequest;
import com.example.mini_sirh.dto.ProjetResponse;
import com.example.mini_sirh.service.ProjetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projets")
@RequiredArgsConstructor
public class ProjetController {

    private final ProjetService projetService;

    @PostMapping
    public ProjetResponse create(@Valid @RequestBody ProjetRequest request) {
        return projetService.create(request);
    }

    @GetMapping
    public List<ProjetResponse> findAll() {
        return projetService.findAll();
    }

    @GetMapping("/{id}")
    public ProjetResponse findById(@PathVariable Long id) {
        return projetService.findById(id);
    }

    @PutMapping("/{id}")
    public ProjetResponse update(@PathVariable Long id, @Valid @RequestBody ProjetRequest request) {
        return projetService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        projetService.delete(id);
    }

    @PostMapping("/{projetId}/collaborateurs/{collaborateurId}")
    public ProjetResponse assignCollaborateur(
            @PathVariable Long projetId,
            @PathVariable Long collaborateurId
    ) {
        return projetService.assignCollaborateur(projetId, collaborateurId);
    }
}