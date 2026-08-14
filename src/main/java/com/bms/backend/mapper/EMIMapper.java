package com.bms.backend.mapper;

import com.bms.backend.dto.EMIResponse;
import com.bms.backend.entity.EMI;

public class EMIMapper {

    private EMIMapper() {
        
    }

    public static EMIResponse toResponse(EMI emi) {

        EMIResponse response = new EMIResponse();

        response.setEmiId(
                emi.getEmiId());

        response.setInstallmentNumber(
                emi.getInstallmentNumber());

        response.setAmount(
                emi.getAmount());

        response.setPaidAmount(
                emi.getPaidAmount());

        response.setRemainingAmount(
                emi.getRemainingAmount());

        response.setPaymentStatus(
                emi.getPaymentStatus());

        if (emi.getLoanApplication() != null) {

            response.setLoanApplicationId(
                    emi.getLoanApplication()
                            .getLoanApplicationId());
        }

        return response;
    }
}