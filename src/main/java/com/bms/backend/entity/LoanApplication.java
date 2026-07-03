package com.bms.backend.entity;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "loan_application")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanApplicationId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String loanAmount;

    private String interestRate;

    private Integer tenureMonths;

    private String emiAmount;

    private String applicationDate;

    private String status;
}