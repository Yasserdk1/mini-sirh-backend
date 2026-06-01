package com.example.mini_sirh.entity;

import com.example.mini_sirh.entity.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean active;

    @OneToOne
    @JoinColumn(name = "collaborateur_id")
    private Collaborateur collaborateur;
}