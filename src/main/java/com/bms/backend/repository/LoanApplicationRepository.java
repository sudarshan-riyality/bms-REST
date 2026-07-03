package com.bms.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bms.backend.entity.Customer;
import com.bms.backend.entity.LoanApplication;

@Repository
public interface LoanApplicationRepository
        extends JpaRepository<LoanApplication, Long> {

    List<LoanApplication> findByCustomer(Customer customer);

}