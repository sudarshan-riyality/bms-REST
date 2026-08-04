package com.bms.backend.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.bms.backend.entity.ApprovalStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanApprovalResponseDto {

    private UUID approvalId;

    private Long loanApplicationId;

    private ApprovalStatus status;

    private String remarks;

    private LocalDate approvalDate;
}