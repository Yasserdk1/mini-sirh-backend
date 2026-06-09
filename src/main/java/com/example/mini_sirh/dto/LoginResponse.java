package com.example.mini_sirh.dto;

import com.example.mini_sirh.entity.enums.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResponse {

    private String message;
    private String username;
    private Role role;
}