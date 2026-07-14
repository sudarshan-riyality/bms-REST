package com.bms.backend.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bms.backend.dto.LoanApplicationRequestDto;
import com.bms.backend.dto.LoanApplicationResponseDto;
import com.bms.backend.service.LoanApplicationService;

@RestController
@RequestMapping("/api/loans")
public class LoanApplicationController {

    @Autowired
    private LoanApplicationService loanApplicationService;

    @PostMapping("/{customerId}")
    public LoanApplicationResponseDto applyLoan(
            @RequestBody LoanApplicationRequestDto requestDto,
            @PathVariable UUID customerId) {

        return loanApplicationService.applyLoan(requestDto, customerId);
    }

    @GetMapping("/{loanApplicationId}")
    public LoanApplicationResponseDto getLoanById(
            @PathVariable Long loanApplicationId) {

        return loanApplicationService.getLoanById(loanApplicationId);
    }

    @GetMapping("/customer/{customerId}")
    public List<LoanApplicationResponseDto> getLoansByCustomerId(
            @PathVariable UUID customerId) {

        return loanApplicationService.getLoansByCustomerId(customerId);
    }
}