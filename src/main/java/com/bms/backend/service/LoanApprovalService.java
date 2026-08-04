package com.bms.backend.service;

import java.util.UUID;

import com.bms.backend.dto.LoanApprovalRequestDto;
import com.bms.backend.dto.LoanApprovalResponseDto;

public interface LoanApprovalService {

    LoanApprovalResponseDto createLoanApproval(
            Long loanApplicationId,
            LoanApprovalRequestDto dto);

    LoanApprovalResponseDto getLoanApprovalById(
            UUID approvalId);

    LoanApprovalResponseDto getLoanApprovalByLoanApplicationId(
            Long loanApplicationId);

    LoanApprovalResponseDto updateLoanApproval(
            UUID approvalId,
            LoanApprovalRequestDto dto);

    void deleteLoanApproval(
            UUID approvalId);
}