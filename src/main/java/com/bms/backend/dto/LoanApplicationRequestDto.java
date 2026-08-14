package com.bms.backend.dto;

import com.bms.backend.entity.LoanType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanApplicationRequestDto {

    @NotNull
    private LoanType loanType;

    @NotNull
    @Positive
    private Double loanAmount;

    @NotNull
    @Positive
    private Double interestRate;

    @NotNull
    @Positive
    private Integer tenureMonths;

    private String applicationDate;
}