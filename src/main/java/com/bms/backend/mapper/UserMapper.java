package com.bms.backend.mapper;

import org.springframework.stereotype.Component;

import com.bms.backend.dto.UserRequestDto;
import com.bms.backend.dto.UserResponseDto;
import com.bms.backend.entity.Customer;
import com.bms.backend.entity.User;

@Component
public class UserMapper {

    public User toEntity(UserRequestDto dto, Customer customer) {

        User user = new User();

        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setCustomer(customer);

        return user;
    }

    public UserResponseDto toDto(User user) {

        UserResponseDto dto = new UserResponseDto();

        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole());

        if (user.getCustomer() != null) {
            dto.setCustomerId(user.getCustomer().getCustomerId());
        }

        return dto;
    }
}