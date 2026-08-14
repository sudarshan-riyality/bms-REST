package com.bms.backend.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bms.backend.dto.LoanApplicationRequestDto;
import com.bms.backend.dto.LoanApplicationResponseDto;
import com.bms.backend.entity.Customer;
import com.bms.backend.entity.LoanApplication;
import com.bms.backend.exception.ResourceNotFoundException;
import com.bms.backend.mapper.LoanApplicationMapper;
import com.bms.backend.repository.CustomerRepository;
import com.bms.backend.repository.LoanApplicationRepository;
import com.bms.backend.service.LoanApplicationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoanApplicationServiceImpl
        implements LoanApplicationService {

    private final LoanApplicationRepository loanApplicationRepository;

    private final CustomerRepository customerRepository;

    @Override
    public LoanApplicationResponseDto applyLoan(
            LoanApplicationRequestDto requestDto,
            UUID customerId) {

        
        Customer customer =
                customerRepository.findById(customerId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Customer not found with ID: "
                                                + customerId));

       
        LoanApplication loanApplication =
                LoanApplicationMapper.toEntity(
                        requestDto,
                        customer);

       
        double emiAmount =
                calculateEMI(
                        requestDto.getLoanAmount(),
                        requestDto.getInterestRate(),
                        requestDto.getTenureMonths());

        
        loanApplication.setEmiAmount(
                emiAmount);

        
        LoanApplication savedLoan =
                loanApplicationRepository.save(
                        loanApplication);

   
        return LoanApplicationMapper
                .toResponseDto(savedLoan);
    }

    /**
     * EMI Formula:
     *
     * EMI = P × r × (1+r)^n / ((1+r)^n - 1)
     *
     * P = Principal / Loan Amount
     * r = Monthly Interest Rate
     * n = Number of Months
     */
    private double calculateEMI(
            double principal,
            double annualInterestRate,
            int tenureMonths) {

        
        double monthlyRate =
                annualInterestRate / 12 / 100;

        
        if (monthlyRate == 0) {

            double emi =
                    principal / tenureMonths;

            return roundToTwoDecimals(emi);
        }

        double power =
                Math.pow(
                        1 + monthlyRate,
                        tenureMonths);

        double emi =
                principal
                * monthlyRate
                * power
                / (power - 1);

        return roundToTwoDecimals(emi);
    }

    private double roundToTwoDecimals(
            double value) {

        return Math.round(
                value * 100.0) / 100.0;
    }

    @Override
    public LoanApplicationResponseDto getLoanById(
            Long loanApplicationId) {

        LoanApplication loanApplication =
                loanApplicationRepository
                        .findById(loanApplicationId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Loan Application not found with ID: "
                                                + loanApplicationId));

        return LoanApplicationMapper
                .toResponseDto(loanApplication);
    }

    @Override
    public List<LoanApplicationResponseDto>
    getLoansByCustomerId(UUID customerId) {

        Customer customer =
                customerRepository.findById(customerId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Customer not found with ID: "
                                                + customerId));

        return customer.getLoans()
                .stream()
                .map(LoanApplicationMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}