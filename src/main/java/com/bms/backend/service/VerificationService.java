package com.bms.backend.service;

import java.util.UUID;

import com.bms.backend.dto.VerificationRequestDto;
import com.bms.backend.dto.VerificationResponseDto;

public interface VerificationService {

    VerificationResponseDto createVerification(
            Long loanApplicationId,
            VerificationRequestDto dto);

    VerificationResponseDto getVerificationById(
            UUID verificationId);

    VerificationResponseDto getVerificationByLoanApplicationId(
            Long loanApplicationId);

    VerificationResponseDto updateVerification(
            UUID verificationId,
            VerificationRequestDto dto);

    void deleteVerification(
            UUID verificationId);
}