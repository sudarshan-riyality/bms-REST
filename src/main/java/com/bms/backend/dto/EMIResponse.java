package com.bms.backend.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EMIResponse {

    private UUID emiId;

    private Integer installmentNumber;

    private Double amount;

    private Double paidAmount;

    private Double remainingAmount;

    private String paymentStatus;

    private Long loanApplicationId;
}