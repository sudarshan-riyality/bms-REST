package com.bms.backend.service;

import com.bms.backend.dto.LoginRequestDto;
import com.bms.backend.dto.LoginResponseDto;

public interface AuthService {

    LoginResponseDto login(LoginRequestDto dto);

}