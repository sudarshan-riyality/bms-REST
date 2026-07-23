package com.bms.backend.dto;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanApplicationRequestDto {

    private String customerId;

    private String loanAmount;

    private String interestRate;

    private Integer tenureMonths;

    private String emiAmount;

    private String applicationDate;

    private String status;
}