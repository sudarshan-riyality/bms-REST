package com.bms.backend.mapper;

import java.time.LocalDate;

import com.bms.backend.dto.LoanApprovalRequestDto;
import com.bms.backend.dto.LoanApprovalResponseDto;
import com.bms.backend.entity.LoanApplication;
import com.bms.backend.entity.LoanApproval;

public class LoanApprovalMapper {

    public static LoanApproval toEntity(
            LoanApprovalRequestDto dto,
            LoanApplication loanApplication) {

        LoanApproval loanApproval = new LoanApproval();

        loanApproval.setLoanApplication(loanApplication);
        loanApproval.setStatus(dto.getStatus());
        loanApproval.setRemarks(dto.getRemarks());
        loanApproval.setApprovalDate(LocalDate.now());

        return loanApproval;
    }

    public static LoanApprovalResponseDto toResponseDto(
            LoanApproval loanApproval) {

        LoanApprovalResponseDto dto = new LoanApprovalResponseDto();

        dto.setApprovalId(loanApproval.getApprovalId());
        dto.setLoanApplicationId(
                loanApproval.getLoanApplication().getLoanApplicationId());
        dto.setStatus(loanApproval.getStatus());
        dto.setRemarks(loanApproval.getRemarks());
        dto.setApprovalDate(loanApproval.getApprovalDate());

        return dto;
    }
}