package com.bms.backend.mapper;

import com.bms.backend.dto.EMIRequest;
import com.bms.backend.dto.EMIRequestdto;
import com.bms.backend.dto.EMIResponse;
import com.bms.backend.entity.EMI;

public class EMIMapper {

    public static EMI toEntity(EMIRequestdto request) {

        EMI emi = new EMI();

        emi.setInstallmentNumber(
                request.getInstallmentNumber());

        emi.setAmount(
                request.getAmount());

        emi.setPaymentStatus(
                request.getPaymentStatus());

        return emi;
    }

    public static EMIResponse toResponse(EMI emi) {

        EMIResponse response = new EMIResponse();

        response.setEmiId(
                emi.getEmiId());

        response.setInstallmentNumber(
                emi.getInstallmentNumber());

        response.setAmount(
                emi.getAmount());

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