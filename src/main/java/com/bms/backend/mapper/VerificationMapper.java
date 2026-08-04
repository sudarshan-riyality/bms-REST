package com.bms.backend.mapper;

import java.time.LocalDate;

import com.bms.backend.dto.VerificationRequestDto;
import com.bms.backend.dto.VerificationResponseDto;
import com.bms.backend.entity.Customer;
import com.bms.backend.entity.Verification;

public class VerificationMapper {

    public static Verification toEntity(VerificationRequestDto dto, Customer customer) {

        Verification verification = new Verification();

        verification.setCustomer(customer);
        verification.setStatus(dto.getStatus());
        verification.setRemarks(dto.getRemarks());
        verification.setVerificationDate(LocalDate.now());

        return verification;
    }

    public static VerificationResponseDto toResponseDto(Verification verification) {

        VerificationResponseDto dto = new VerificationResponseDto();

        dto.setVerificationId(verification.getVerificationId());
        dto.setCustomerId(verification.getCustomer().getCustomerId());
        dto.setStatus(verification.getStatus());
        dto.setRemarks(verification.getRemarks());
        dto.setVerificationDate(verification.getVerificationDate());

        return dto;
    }
}