package com.bms.backend.service;

import java.util.List;
import java.util.UUID;

import com.bms.backend.dto.UserRequestDto;
import com.bms.backend.dto.UserResponseDto;

public interface UserService {

    UserResponseDto createUser(UserRequestDto dto);

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUserById(UUID userId);

    UserResponseDto updateUser(UUID userId, UserRequestDto dto);

    void deleteUser(UUID userId);
}