package com.example.mini_sirh.service;

import com.example.mini_sirh.dto.LoginRequest;
import com.example.mini_sirh.dto.LoginResponse;
import com.example.mini_sirh.entity.User;
import com.example.mini_sirh.exception.ResourceNotFoundException;
import com.example.mini_sirh.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur introuvable"));

        if (!Boolean.TRUE.equals(user.getActive())) {
            throw new RuntimeException("Compte désactivé");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Mot de passe incorrect");
        }

        return LoginResponse.builder()
                .message("Connexion réussie")
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }
}