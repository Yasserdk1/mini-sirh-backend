package com.example.mini_sirh.controller;

import com.example.mini_sirh.dto.LoginRequest;
import com.example.mini_sirh.dto.LoginResponse;
import com.example.mini_sirh.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
}