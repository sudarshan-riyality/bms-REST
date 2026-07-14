package com.bms.backend.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bms.backend.dto.LoanApplicationRequestDto;
import com.bms.backend.dto.LoanApplicationResponseDto;
import com.bms.backend.entity.Customer;
import com.bms.backend.entity.LoanApplication;
import com.bms.backend.mapper.LoanApplicationMapper;
import com.bms.backend.repository.CustomerRepository;
import com.bms.backend.repository.LoanApplicationRepository;
import com.bms.backend.service.LoanApplicationService;

@Service
public class LoanApplicationServiceImpl implements LoanApplicationService {

    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public LoanApplicationResponseDto applyLoan(
            LoanApplicationRequestDto requestDto,
            UUID customerId) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));

        LoanApplication loanApplication =
                LoanApplicationMapper.toEntity(requestDto, customer);

        LoanApplication savedLoan =
                loanApplicationRepository.save(loanApplication);

        return LoanApplicationMapper.toResponseDto(savedLoan);
    }

    @Override
    public LoanApplicationResponseDto getLoanById(Long loanApplicationId) {

        LoanApplication loanApplication =
                loanApplicationRepository.findById(loanApplicationId)
                        .orElseThrow(() ->
                                new RuntimeException("Loan Application not found"));

        return LoanApplicationMapper.toResponseDto(loanApplication);
    }

    @Override
    public List<LoanApplicationResponseDto> getLoansByCustomerId(
            UUID customerId) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));

        return customer.getLoans()
                .stream()
                .map(LoanApplicationMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}