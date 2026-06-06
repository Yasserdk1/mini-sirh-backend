package com.example.mini_sirh.controller;

import com.example.mini_sirh.dto.CongeRequest;
import com.example.mini_sirh.entity.Conge;
import com.example.mini_sirh.service.CongeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conges")
@RequiredArgsConstructor
public class CongeController {

    private final CongeService congeService;

    @PostMapping
    public Conge create(@Valid @RequestBody CongeRequest request) {
        return congeService.create(request);
    }

    @GetMapping
    public List<Conge> findAll() {
        return congeService.findAll();
    }

    @GetMapping("/{id}")
    public Conge findById(@PathVariable Long id) {
        return congeService.findById(id);
    }

    @PutMapping("/{id}")
    public Conge update(@PathVariable Long id, @Valid @RequestBody CongeRequest request) {
        return congeService.update(id, request);
    }

    @PutMapping("/{id}/accepter")
    public Conge accepter(@PathVariable Long id) {
        return congeService.accepter(id);
    }

    @PutMapping("/{id}/refuser")
    public Conge refuser(@PathVariable Long id) {
        return congeService.refuser(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        congeService.delete(id);
    }
}