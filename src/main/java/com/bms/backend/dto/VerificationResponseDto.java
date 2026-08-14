package com.bms.backend.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.bms.backend.entity.VerificationStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerificationResponseDto {

    private UUID verificationId;

    private Long loanApplicationId;

    private VerificationStatus status;

    private String remarks;

    private LocalDate verificationDate;
}