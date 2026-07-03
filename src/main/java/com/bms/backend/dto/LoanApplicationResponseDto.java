package com.bms.backend.dto;

import lombok.Data;

@Data
public class LoanApplicationResponseDto {

    private Long loanApplicationId;

    private String customerId;

    private String loanAmount;

    private String interestRate;

    private Integer tenureMonths;

    private String emiAmount;

    private String applicationDate;

    private String status;

}