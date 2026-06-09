package com.example.mini_sirh.controller;

import com.example.mini_sirh.dto.DepartementRequest;
import com.example.mini_sirh.dto.DepartementResponse;
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
    public DepartementResponse create(@Valid @RequestBody DepartementRequest request) {
        return departementService.create(request);
    }

    @GetMapping
    public List<DepartementResponse> findAll() {
        return departementService.findAll();
    }

    @GetMapping("/{id}")
    public DepartementResponse findById(@PathVariable Long id) {
        return departementService.findById(id);
    }

    @PutMapping("/{id}")
    public DepartementResponse update(@PathVariable Long id, @Valid @RequestBody DepartementRequest request) {
        return departementService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        departementService.delete(id);
    }
}