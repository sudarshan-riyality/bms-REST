package com.bms.backend.service;

import java.util.List;

import com.bms.backend.dto.LoanApplicationRequestDto;
import com.bms.backend.dto.LoanApplicationResponseDto;

public interface LoanApplicationService {

    
    LoanApplicationResponseDto applyLoan(LoanApplicationRequestDto requestDto, String customerId);

   
    LoanApplicationResponseDto getLoanById(Long loanApplicationId);

    
    List<LoanApplicationResponseDto> getLoansByCustomerId(String customerId);

}