package com.example.mini_sirh.controller;

import com.example.mini_sirh.dto.DashboardResponse;
import com.example.mini_sirh.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/stats")
    public DashboardResponse getStats() {
        return dashboardService.getStats();
    }
}