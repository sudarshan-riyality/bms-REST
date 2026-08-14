package com.bms.backend.mapper;

import java.time.LocalDate;

import com.bms.backend.dto.VerificationRequestDto;
import com.bms.backend.dto.VerificationResponseDto;
import com.bms.backend.entity.LoanApplication;
import com.bms.backend.entity.Verification;
import com.bms.backend.entity.VerificationStatus;

public class VerificationMapper {

    
    public static Verification toEntity(
            VerificationRequestDto dto,
            LoanApplication loanApplication) {

        Verification verification = new Verification();

        verification.setLoanApplication(loanApplication);

        
        verification.setStatus(VerificationStatus.PENDING);

        verification.setRemarks(dto.getRemarks());

        verification.setVerificationDate(LocalDate.now());

        return verification;
    }

    
    public static VerificationResponseDto toResponseDto(
            Verification verification) {

        VerificationResponseDto dto =
                new VerificationResponseDto();

        dto.setVerificationId(
                verification.getVerificationId());

        dto.setLoanApplicationId(
                verification.getLoanApplication()
                        .getLoanApplicationId());

        dto.setStatus(
                verification.getStatus());

        dto.setRemarks(
                verification.getRemarks());

        dto.setVerificationDate(
                verification.getVerificationDate());

        return dto;
    }
}