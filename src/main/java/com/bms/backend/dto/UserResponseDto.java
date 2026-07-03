package com.bms.backend.dto;

import java.util.UUID;

import com.bms.backend.entity.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDto {

    private UUID userId;
    private String username;
    private Role role;
    private UUID customerId;
}