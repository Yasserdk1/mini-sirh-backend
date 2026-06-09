package com.example.mini_sirh.controller;

import com.example.mini_sirh.dto.CongeRequest;
import com.example.mini_sirh.dto.CongeResponse;
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
    public CongeResponse create(@Valid @RequestBody CongeRequest request) {
        return congeService.create(request);
    }

    @GetMapping
    public List<CongeResponse> findAll() {
        return congeService.findAll();
    }

    @GetMapping("/{id}")
    public CongeResponse findById(@PathVariable Long id) {
        return congeService.findById(id);
    }

    @PutMapping("/{id}")
    public CongeResponse update(@PathVariable Long id, @Valid @RequestBody CongeRequest request) {
        return congeService.update(id, request);
    }

    @PutMapping("/{id}/accepter")
    public CongeResponse accepter(@PathVariable Long id) {
        return congeService.accepter(id);
    }

    @PutMapping("/{id}/refuser")
    public CongeResponse refuser(@PathVariable Long id) {
        return congeService.refuser(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        congeService.delete(id);
    }
}