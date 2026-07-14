package com.bms.backend.service;

import java.util.List;
import java.util.UUID;

import com.bms.backend.dto.LoanApplicationRequestDto;
import com.bms.backend.dto.LoanApplicationResponseDto;

public interface LoanApplicationService {

    LoanApplicationResponseDto applyLoan(
            LoanApplicationRequestDto requestDto,
            UUID customerId);

    LoanApplicationResponseDto getLoanById(Long loanApplicationId);

    List<LoanApplicationResponseDto> getLoansByCustomerId(UUID customerId);

}