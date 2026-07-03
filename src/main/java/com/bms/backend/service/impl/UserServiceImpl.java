package com.bms.backend.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bms.backend.dto.UserRequestDto;
import com.bms.backend.dto.UserResponseDto;
import com.bms.backend.entity.Customer;
import com.bms.backend.entity.User;
import com.bms.backend.mapper.UserMapper;
import com.bms.backend.repository.CustomerRepository;
import com.bms.backend.repository.UserRepository;
import com.bms.backend.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto createUser(UserRequestDto dto) {

        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("Username Already Exists");
        }

        Customer customer = null;

        if (dto.getCustomerId() != null) {
            customer = customerRepository.findById(dto.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer Not Found"));
        }

        User user = userMapper.toEntity(dto, customer);

        User savedUser = userRepository.save(user);

        return userMapper.toDto(savedUser);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public UserResponseDto getUserById(UUID userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        return userMapper.toDto(user);
    }

    @Override
    public UserResponseDto updateUser(UUID userId, UserRequestDto dto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());

        if (dto.getCustomerId() != null) {

            Customer customer = customerRepository.findById(dto.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer Not Found"));

            user.setCustomer(customer);
        }

        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public void deleteUser(UUID userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        userRepository.delete(user);
    }
}