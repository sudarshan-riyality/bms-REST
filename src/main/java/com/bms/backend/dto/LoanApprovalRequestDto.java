package com.bms.backend.dto;

import com.bms.backend.entity.ApprovalStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanApprovalRequestDto {

    private ApprovalStatus status;

    private String remarks;
}