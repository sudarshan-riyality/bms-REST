package com.bms.backend.service;

import java.util.UUID;

import com.bms.backend.dto.VerificationRequestDto;
import com.bms.backend.dto.VerificationResponseDto;

public interface VerificationService {

    VerificationResponseDto createVerification(
            UUID customerId,
            VerificationRequestDto dto);

    VerificationResponseDto getVerificationById(
            UUID verificationId);

    VerificationResponseDto getVerificationByCustomerId(
            UUID customerId);

    VerificationResponseDto updateVerification(
            UUID verificationId,
            VerificationRequestDto dto);

    void deleteVerification(
            UUID verificationId);
}