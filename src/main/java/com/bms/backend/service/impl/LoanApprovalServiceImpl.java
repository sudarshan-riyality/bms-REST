package com.bms.backend.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bms.backend.dto.LoanApprovalRequestDto;
import com.bms.backend.dto.LoanApprovalResponseDto;
import com.bms.backend.entity.LoanApplication;
import com.bms.backend.entity.LoanApproval;
import com.bms.backend.exception.ResourceNotFoundException;
import com.bms.backend.mapper.LoanApprovalMapper;
import com.bms.backend.repository.LoanApplicationRepository;
import com.bms.backend.repository.LoanApprovalRepository;
import com.bms.backend.service.LoanApprovalService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoanApprovalServiceImpl implements LoanApprovalService {

	
	private final LoanApprovalRepository loanApprovalRepository;

	private final LoanApplicationRepository loanApplicationRepository;
	
	@Override
	public LoanApprovalResponseDto createLoanApproval(
	        Long loanApplicationId,
	        LoanApprovalRequestDto dto) {

	    LoanApplication loanApplication = loanApplicationRepository
	            .findById(loanApplicationId)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException(
	                            "Loan Application not found with ID: " + loanApplicationId));

	    LoanApproval loanApproval =
	            LoanApprovalMapper.toEntity(dto, loanApplication);

	    LoanApproval savedLoanApproval =
	            loanApprovalRepository.save(loanApproval);

	    return LoanApprovalMapper.toResponseDto(savedLoanApproval);
	}

	@Override
	public LoanApprovalResponseDto getLoanApprovalById(
	        UUID approvalId) {

	    LoanApproval loanApproval = loanApprovalRepository
	            .findById(approvalId)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException(
	                            "Loan Approval not found with ID: " + approvalId));

	    return LoanApprovalMapper.toResponseDto(loanApproval);
	}

	@Override
	public LoanApprovalResponseDto getLoanApprovalByLoanApplicationId(
	        Long loanApplicationId) {

	    LoanApplication loanApplication = loanApplicationRepository
	            .findById(loanApplicationId)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException(
	                            "Loan Application not found with ID: " + loanApplicationId));

	    LoanApproval loanApproval = loanApprovalRepository
	            .findByLoanApplication(loanApplication)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException(
	                            "Loan Approval not found for Loan Application ID: " + loanApplicationId));

	    return LoanApprovalMapper.toResponseDto(loanApproval);
	}

	@Override
	public LoanApprovalResponseDto updateLoanApproval(
	        UUID approvalId,
	        LoanApprovalRequestDto dto) {

	    LoanApproval loanApproval = loanApprovalRepository
	            .findById(approvalId)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException(
	                            "Loan Approval not found with ID: " + approvalId));

	    loanApproval.setStatus(dto.getStatus());
	    loanApproval.setRemarks(dto.getRemarks());

	    LoanApproval updatedLoanApproval =
	            loanApprovalRepository.save(loanApproval);

	    return LoanApprovalMapper.toResponseDto(updatedLoanApproval);
	}

	@Override
	public void deleteLoanApproval(
	        UUID approvalId) {

	    LoanApproval loanApproval = loanApprovalRepository
	            .findById(approvalId)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException(
	                            "Loan Approval not found with ID: " + approvalId));

	    loanApprovalRepository.delete(loanApproval);
	}
}