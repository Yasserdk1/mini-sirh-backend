package com.example.mini_sirh.controller;

import com.example.mini_sirh.dto.DepartementRequest;
import com.example.mini_sirh.entity.Departement;
import com.example.mini_sirh.service.DepartementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departements")
@RequiredArgsConstructor
public class DepartementController {

    private final DepartementService departementService;

    @PostMapping
    public Departement create(@Valid @RequestBody DepartementRequest request) {
        return departementService.create(request);
    }

    @GetMapping
    public List<Departement> findAll() {
        return departementService.findAll();
    }

    @GetMapping("/{id}")
    public Departement findById(@PathVariable Long id) {
        return departementService.findById(id);
    }

    @PutMapping("/{id}")
    public Departement update(@PathVariable Long id, @Valid @RequestBody DepartementRequest request) {
        return departementService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        departementService.delete(id);
    }
}