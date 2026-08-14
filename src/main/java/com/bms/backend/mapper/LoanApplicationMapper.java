package com.bms.backend.mapper;

import com.bms.backend.dto.LoanApplicationRequestDto;
import com.bms.backend.dto.LoanApplicationResponseDto;
import com.bms.backend.entity.Customer;
import com.bms.backend.entity.LoanApplication;

public class LoanApplicationMapper {

    public static LoanApplication toEntity(
            LoanApplicationRequestDto dto,
            Customer customer) {

        LoanApplication loan = new LoanApplication();

        loan.setCustomer(customer);

        loan.setLoanType(
                dto.getLoanType());

        loan.setLoanAmount(
                dto.getLoanAmount());

        loan.setInterestRate(
                dto.getInterestRate());

        loan.setTenureMonths(
                dto.getTenureMonths());

        loan.setApplicationDate(
                dto.getApplicationDate());

     
        loan.setStatus("PENDING");

        return loan;
    }

    public static LoanApplicationResponseDto toResponseDto(
            LoanApplication loan) {

        LoanApplicationResponseDto dto =
                new LoanApplicationResponseDto();

        dto.setLoanApplicationId(
                loan.getLoanApplicationId());

        if (loan.getCustomer() != null) {
            dto.setCustomerId(
                    loan.getCustomer()
                        .getCustomerId()
                        .toString());
        }

        dto.setLoanType(
                loan.getLoanType());

        dto.setLoanAmount(
                loan.getLoanAmount());

        dto.setInterestRate(
                loan.getInterestRate());

        dto.setTenureMonths(
                loan.getTenureMonths());

        dto.setEmiAmount(
                loan.getEmiAmount());

        dto.setApplicationDate(
                loan.getApplicationDate());

        dto.setStatus(
                loan.getStatus());

        return dto;
    }
}