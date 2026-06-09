package com.example.mini_sirh.service;

import com.example.mini_sirh.dto.UserRequest;
import com.example.mini_sirh.dto.UserResponse;
import com.example.mini_sirh.entity.Collaborateur;
import com.example.mini_sirh.entity.User;
import com.example.mini_sirh.exception.DuplicateResourceException;
import com.example.mini_sirh.exception.ResourceNotFoundException;
import com.example.mini_sirh.repository.CollaborateurRepository;
import com.example.mini_sirh.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CollaborateurRepository collaborateurRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse create(UserRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateResourceException("Ce nom d'utilisateur existe déjà : " + request.getUsername());
        }

        Collaborateur collaborateur = null;

        if (request.getCollaborateurId() != null) {
            collaborateur = collaborateurRepository.findById(request.getCollaborateurId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Collaborateur introuvable avec l'id : " + request.getCollaborateurId()
                    ));

            if (userRepository.existsByCollaborateur(collaborateur)) {
                throw new DuplicateResourceException(
                        "Ce collaborateur possède déjà un compte utilisateur"
                );
            }
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .active(true)
                .collaborateur(collaborateur)
                .build();

        return mapToResponse(userRepository.save(user));
    }

    public List<UserResponse> findAll() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public UserResponse findById(Long id) {
        return mapToResponse(getEntityById(id));
    }

    public void delete(Long id) {
        User user = getEntityById(id);
        userRepository.delete(user);
    }

    private User getEntityById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur introuvable avec l'id : " + id));
    }

    private UserResponse mapToResponse(User user) {
        Collaborateur collaborateur = user.getCollaborateur();

        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .role(user.getRole())
                .active(user.getActive())
                .collaborateurId(collaborateur != null ? collaborateur.getId() : null)
                .collaborateurNomComplet(collaborateur != null ? collaborateur.getNom() + " " + collaborateur.getPrenom() : null)
                .build();
    }
}