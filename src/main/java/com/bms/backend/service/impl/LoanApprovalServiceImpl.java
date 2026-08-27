package com.bms.backend.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bms.backend.dto.LoanApprovalRequestDto;
import com.bms.backend.dto.LoanApprovalResponseDto;
import com.bms.backend.entity.ApprovalStatus;
import com.bms.backend.entity.LoanApplication;
import com.bms.backend.entity.LoanApproval;
import com.bms.backend.entity.Verification;
import com.bms.backend.entity.VerificationStatus;
import com.bms.backend.exception.ResourceNotFoundException;
import com.bms.backend.mapper.LoanApprovalMapper;
import com.bms.backend.repository.LoanApplicationRepository;
import com.bms.backend.repository.LoanApprovalRepository;
import com.bms.backend.repository.VerificationRepository;
import com.bms.backend.service.LoanApprovalService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoanApprovalServiceImpl
        implements LoanApprovalService {

    private final LoanApprovalRepository loanApprovalRepository;

    private final LoanApplicationRepository loanApplicationRepository;

    private final VerificationRepository verificationRepository;

    @Override
    @Transactional
    public LoanApprovalResponseDto createLoanApproval(
            Long loanApplicationId,
            LoanApprovalRequestDto dto) {

       
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

     
        if (verification.getStatus()
                != VerificationStatus.VERIFIED) {

            throw new IllegalStateException(
                    "Loan cannot be approved because verification is not completed. "
                    + "Current verification status: "
                    + verification.getStatus());
        }

        
        if (loanApprovalRepository
                .findByLoanApplication(loanApplication)
                .isPresent()) {

            throw new IllegalStateException(
                    "Loan Approval already exists for Loan Application ID: "
                            + loanApplicationId);
        }

       
        LoanApproval loanApproval =
                LoanApprovalMapper.toEntity(
                        dto,
                        loanApplication);

       
        LoanApproval savedLoanApproval =
                loanApprovalRepository.save(loanApproval);

        
        if (dto.getStatus() == ApprovalStatus.APPROVED) {

            loanApplication.setStatus("APPROVED");

        } else if (dto.getStatus() == ApprovalStatus.REJECTED) {

            loanApplication.setStatus("REJECTED");

        } else {

            loanApplication.setStatus("PENDING");
        }

    
        loanApplicationRepository.save(loanApplication);

    
        return LoanApprovalMapper.toResponseDto(
                savedLoanApproval);
    }

    @Override
    public LoanApprovalResponseDto getLoanApprovalById(
            UUID approvalId) {

        LoanApproval loanApproval =
                loanApprovalRepository.findById(approvalId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Loan Approval not found with ID: "
                                                + approvalId));

        return LoanApprovalMapper.toResponseDto(
                loanApproval);
    }

    @Override
    public LoanApprovalResponseDto
    getLoanApprovalByLoanApplicationId(
            Long loanApplicationId) {

        LoanApplication loanApplication =
                loanApplicationRepository.findById(loanApplicationId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Loan Application not found with ID: "
                                                + loanApplicationId));

        LoanApproval loanApproval =
                loanApprovalRepository
                        .findByLoanApplication(loanApplication)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Loan Approval not found for Loan Application ID: "
                                                + loanApplicationId));

        return LoanApprovalMapper.toResponseDto(
                loanApproval);
    }

    @Override
    @Transactional
    public LoanApprovalResponseDto updateLoanApproval(
            UUID approvalId,
            LoanApprovalRequestDto dto) {

        
        LoanApproval loanApproval =
                loanApprovalRepository.findById(approvalId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Loan Approval not found with ID: "
                                                + approvalId));

      
        LoanApplication loanApplication =
                loanApproval.getLoanApplication();

     
        Verification verification =
                verificationRepository
                        .findByLoanApplication(loanApplication)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Verification not found for Loan Application ID: "
                                                + loanApplication
                                                        .getLoanApplicationId()));

      
        if (verification.getStatus()
                != VerificationStatus.VERIFIED) {

            throw new IllegalStateException(
                    "Loan cannot be approved because verification is not completed. "
                    + "Current verification status: "
                    + verification.getStatus());
        }

  
        if (dto.getStatus() != null) {
            loanApproval.setStatus(dto.getStatus());
        }

        if (dto.getRemarks() != null) {
            loanApproval.setRemarks(dto.getRemarks());
        }

        
        if (dto.getStatus() == ApprovalStatus.APPROVED) {

            loanApplication.setStatus("APPROVED");

        } else if (dto.getStatus() == ApprovalStatus.REJECTED) {

            loanApplication.setStatus("REJECTED");
        }

        
        LoanApproval updatedLoanApproval =
                loanApprovalRepository.save(loanApproval);

        loanApplicationRepository.save(loanApplication);

      
        return LoanApprovalMapper.toResponseDto(
                updatedLoanApproval);
    }

    @Override
    public void deleteLoanApproval(
            UUID approvalId) {

        LoanApproval loanApproval =
                loanApprovalRepository.findById(approvalId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Loan Approval not found with ID: "
                                                + approvalId));

        loanApprovalRepository.delete(loanApproval);
    }
}