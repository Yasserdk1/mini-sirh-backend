package com.example.mini_sirh.controller;

import com.example.mini_sirh.dto.CollaborateurRequest;
import com.example.mini_sirh.dto.CollaborateurResponse;
import com.example.mini_sirh.service.CollaborateurService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collaborateurs")
@RequiredArgsConstructor
public class CollaborateurController {

    private final CollaborateurService collaborateurService;

    @PostMapping
    public CollaborateurResponse create(@Valid @RequestBody CollaborateurRequest request) {
        return collaborateurService.create(request);
    }

    @GetMapping
    public List<CollaborateurResponse> findAll() {
        return collaborateurService.findAll();
    }

    @GetMapping("/{id}")
    public CollaborateurResponse findById(@PathVariable Long id) {
        return collaborateurService.findById(id);
    }

    @PutMapping("/{id}")
    public CollaborateurResponse update(@PathVariable Long id, @Valid @RequestBody CollaborateurRequest request) {
        return collaborateurService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        collaborateurService.delete(id);
    }
}