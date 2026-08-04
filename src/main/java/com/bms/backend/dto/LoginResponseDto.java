package com.bms.backend.dto;

import java.util.UUID;

import com.bms.backend.entity.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {

    private UUID userId;

    private String username;

    private Role role;

    private String message;
}