package com.bms.backend.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bms.backend.entity.LoanApplication;
import com.bms.backend.entity.LoanApproval;

public interface LoanApprovalRepository
        extends JpaRepository<LoanApproval, UUID> {

    Optional<LoanApproval> findByLoanApplication(
            LoanApplication loanApplication);
}