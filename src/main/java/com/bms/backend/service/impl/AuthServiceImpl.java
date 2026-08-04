package com.bms.backend.service.impl;

import org.springframework.stereotype.Service;

import com.bms.backend.dto.LoginRequestDto;
import com.bms.backend.dto.LoginResponseDto;
import com.bms.backend.entity.User;
import com.bms.backend.exception.ResourceNotFoundException;
import com.bms.backend.repository.UserRepository;
import com.bms.backend.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;
	
	@Override
	public LoginResponseDto login(LoginRequestDto dto) {

	    User user = userRepository.findByUsername(dto.getUsername())
	            .orElseThrow(() ->
	                    new ResourceNotFoundException("Invalid username or password"));

	    if (!user.getPassword().equals(dto.getPassword())) {
	        throw new RuntimeException("Invalid Password");
	    }

	    LoginResponseDto response = new LoginResponseDto();

	    response.setUserId(user.getUserId());
	    response.setUsername(user.getUsername());
	    response.setRole(user.getRole());
	    response.setMessage("Login Successful");

	    return response;
	}

}