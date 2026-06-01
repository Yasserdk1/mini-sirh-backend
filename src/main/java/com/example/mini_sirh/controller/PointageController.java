package com.example.mini_sirh.controller;

import com.example.mini_sirh.dto.PointageRequest;
import com.example.mini_sirh.entity.Pointage;
import com.example.mini_sirh.service.PointageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pointages")
@RequiredArgsConstructor
public class PointageController {

    private final PointageService pointageService;

    @PostMapping
    public Pointage enregistrerPointage(@Valid @RequestBody PointageRequest request) {
        return pointageService.enregistrerPointage(request);
    }

    @GetMapping("/today")
    public List<Pointage> getPointagesDuJour() {
        return pointageService.getPointagesDuJour();
    }

    @GetMapping
    public List<Pointage> findAll() {
        return pointageService.findAll();
    }
}