package com.bms.backend.mapper;

import com.bms.backend.dto.LoanApplicationRequestDto;
import com.bms.backend.dto.LoanApplicationResponseDto;
import com.bms.backend.entity.Customer;
import com.bms.backend.entity.LoanApplication;

public class LoanApplicationMapper {

    public static LoanApplication toEntity(
            LoanApplicationRequestDto dto,
            Customer customer) {

        LoanApplication loanApplication = new LoanApplication();

        loanApplication.setCustomer(customer);
        loanApplication.setLoanAmount(dto.getLoanAmount());
        loanApplication.setInterestRate(dto.getInterestRate());
        loanApplication.setTenureMonths(dto.getTenureMonths());
        loanApplication.setEmiAmount(dto.getEmiAmount());
        loanApplication.setApplicationDate(dto.getApplicationDate());
        loanApplication.setStatus(dto.getStatus());

        return loanApplication;
    }

    public static LoanApplicationResponseDto toResponseDto(
            LoanApplication loanApplication) {

        LoanApplicationResponseDto dto =
                new LoanApplicationResponseDto();

        dto.setLoanApplicationId(
                loanApplication.getLoanApplicationId());

        dto.setCustomerId(
                loanApplication.getCustomer()
                               .getCustomerId()
                               .toString());

        dto.setLoanAmount(
                loanApplication.getLoanAmount());

        dto.setInterestRate(
                loanApplication.getInterestRate());

        dto.setTenureMonths(
                loanApplication.getTenureMonths());

        dto.setEmiAmount(
                loanApplication.getEmiAmount());

        dto.setApplicationDate(
                loanApplication.getApplicationDate());

        dto.setStatus(
                loanApplication.getStatus());

        return dto;
    }
}