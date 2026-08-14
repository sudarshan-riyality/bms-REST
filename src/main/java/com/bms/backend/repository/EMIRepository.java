package com.bms.backend.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bms.backend.entity.EMI;
import com.bms.backend.entity.LoanApplication;

@Repository
public interface EMIRepository
        extends JpaRepository<EMI, UUID> {

    List<EMI> findByLoanApplicationOrderByInstallmentNumberAsc(
            LoanApplication loanApplication);

    EMI findByLoanApplicationAndInstallmentNumber(
            LoanApplication loanApplication,
            Integer installmentNumber);
}