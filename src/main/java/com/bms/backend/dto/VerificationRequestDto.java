package com.bms.backend.dto;

import com.bms.backend.entity.VerificationStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerificationRequestDto {

    private VerificationStatus status;

    private String remarks;
}