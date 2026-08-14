package com.bms.backend.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bms.backend.entity.LoanApplication;
import com.bms.backend.entity.Verification;

public interface VerificationRepository
        extends JpaRepository<Verification, UUID> {

    Optional<Verification> findByLoanApplication(
            LoanApplication loanApplication);
}