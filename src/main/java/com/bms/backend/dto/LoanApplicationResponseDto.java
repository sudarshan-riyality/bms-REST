package com.bms.backend.dto;

import com.bms.backend.entity.LoanType;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanApplicationResponseDto {

    private Long loanApplicationId;

    private String customerId;

    private LoanType loanType;

    private Double loanAmount;

    private Double interestRate;

    private Integer tenureMonths;

    private Double emiAmount;

    private String applicationDate;

    private String status;
}