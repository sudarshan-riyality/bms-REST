package com.bms.backend.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bms.backend.dto.VerificationRequestDto;
import com.bms.backend.dto.VerificationResponseDto;
import com.bms.backend.entity.LoanApplication;
import com.bms.backend.entity.Verification;
import com.bms.backend.exception.ResourceNotFoundException;
import com.bms.backend.mapper.VerificationMapper;
import com.bms.backend.repository.LoanApplicationRepository;
import com.bms.backend.repository.VerificationRepository;
import com.bms.backend.service.VerificationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VerificationServiceImpl
        implements VerificationService {

    private final VerificationRepository verificationRepository;

    private final LoanApplicationRepository loanApplicationRepository;

    @Override
    public VerificationResponseDto createVerification(
            Long loanApplicationId,
            VerificationRequestDto dto) {

        
        LoanApplication loanApplication =
                loanApplicationRepository.findById(loanApplicationId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Loan Application not found with ID: "
                                                + loanApplicationId));

     
        if (verificationRepository
                .findByLoanApplication(loanApplication)
                .isPresent()) {

            throw new IllegalStateException(
                    "Verification already exists for Loan Application ID: "
                            + loanApplicationId);
        }

        
        Verification verification =
                VerificationMapper.toEntity(
                        dto,
                        loanApplication);

      
        Verification savedVerification =
                verificationRepository.save(verification);

        return VerificationMapper.toResponseDto(
                savedVerification);
    }

    @Override
    public VerificationResponseDto getVerificationById(
            UUID verificationId) {

        Verification verification =
                verificationRepository.findById(verificationId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Verification not found with ID: "
                                                + verificationId));

        return VerificationMapper.toResponseDto(
                verification);
    }

    @Override
    public VerificationResponseDto getVerificationByLoanApplicationId(
            Long loanApplicationId) {

        LoanApplication loanApplication =
                loanApplicationRepository.findById(loanApplicationId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Loan Application not found with ID: "
                                                + loanApplicationId));

        Verification verification =
                verificationRepository
                        .findByLoanApplication(loanApplication)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Verification not found for Loan Application ID: "
                                                + loanApplicationId));

        return VerificationMapper.toResponseDto(
                verification);
    }

    @Override
    public VerificationResponseDto updateVerification(
            UUID verificationId,
            VerificationRequestDto dto) {

        Verification verification =
                verificationRepository.findById(verificationId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Verification not found with ID: "
                                                + verificationId));

   
        if (dto.getStatus() != null) {
            verification.setStatus(dto.getStatus());
        }

        
        if (dto.getRemarks() != null) {
            verification.setRemarks(dto.getRemarks());
        }

        Verification updatedVerification =
                verificationRepository.save(verification);

        return VerificationMapper.toResponseDto(
                updatedVerification);
    }

    @Override
    public void deleteVerification(
            UUID verificationId) {

        Verification verification =
                verificationRepository.findById(verificationId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Verification not found with ID: "
                                                + verificationId));

        verificationRepository.delete(verification);
    }
}