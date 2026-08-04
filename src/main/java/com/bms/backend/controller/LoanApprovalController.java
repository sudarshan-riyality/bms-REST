package com.bms.backend.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bms.backend.dto.LoanApprovalRequestDto;
import com.bms.backend.dto.LoanApprovalResponseDto;
import com.bms.backend.service.LoanApprovalService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/loan-approvals")
@RequiredArgsConstructor
public class LoanApprovalController {

    private final LoanApprovalService loanApprovalService;

    @PostMapping("/{loanApplicationId}")
    public ResponseEntity<LoanApprovalResponseDto> createLoanApproval(
            @PathVariable Long loanApplicationId,
            @RequestBody LoanApprovalRequestDto dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(loanApprovalService.createLoanApproval(loanApplicationId, dto));
    }

    @GetMapping("/{approvalId}")
    public ResponseEntity<LoanApprovalResponseDto> getLoanApprovalById(
            @PathVariable UUID approvalId) {

        return ResponseEntity.ok(
                loanApprovalService.getLoanApprovalById(approvalId));
    }

    @GetMapping("/loan/{loanApplicationId}")
    public ResponseEntity<LoanApprovalResponseDto> getLoanApprovalByLoanApplicationId(
            @PathVariable Long loanApplicationId) {

        return ResponseEntity.ok(
                loanApprovalService.getLoanApprovalByLoanApplicationId(loanApplicationId));
    }

    @PutMapping("/{approvalId}")
    public ResponseEntity<LoanApprovalResponseDto> updateLoanApproval(
            @PathVariable UUID approvalId,
            @RequestBody LoanApprovalRequestDto dto) {

        return ResponseEntity.ok(
                loanApprovalService.updateLoanApproval(approvalId, dto));
    }

    @DeleteMapping("/{approvalId}")
    public ResponseEntity<String> deleteLoanApproval(
            @PathVariable UUID approvalId) {

        loanApprovalService.deleteLoanApproval(approvalId);

        return ResponseEntity.ok("Loan Approval Deleted Successfully");
    }
}