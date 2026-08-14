package com.bms.backend.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bms.backend.dto.EMIRequestdto;
import com.bms.backend.dto.EMIResponse;
import com.bms.backend.entity.EMI;
import com.bms.backend.entity.LoanApplication;
import com.bms.backend.exception.ResourceNotFoundException;
import com.bms.backend.mapper.EMIMapper;
import com.bms.backend.repository.EMIRepository;
import com.bms.backend.repository.LoanApplicationRepository;
import com.bms.backend.service.EMIService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EMIServiceImpl implements EMIService {

    private final EMIRepository emiRepository;

    private final LoanApplicationRepository loanApplicationRepository;


    
    @Override
    @Transactional
    public List<EMIResponse> generateEMIs(
            Long loanApplicationId) {

        
        LoanApplication loan =
                loanApplicationRepository
                        .findById(loanApplicationId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Loan Application not found with ID: "
                                                + loanApplicationId));


       

        if (!"APPROVED".equalsIgnoreCase(
                loan.getStatus())) {

            throw new IllegalArgumentException(
                    "EMI can be generated only for an APPROVED loan");
        }


       
        List<EMI> existingEMIs =
                emiRepository
                        .findByLoanApplicationOrderByInstallmentNumberAsc(
                                loan);

        if (!existingEMIs.isEmpty()) {

            return existingEMIs
                    .stream()
                    .map(EMIMapper::toResponse)
                    .collect(Collectors.toList());
        }


      
        Double emiAmount =
                loan.getEmiAmount();


       

        Integer tenureMonths =
                loan.getTenureMonths();


        
        if (emiAmount == null ||
                emiAmount <= 0) {

            throw new IllegalArgumentException(
                    "EMI amount is not available for this loan");
        }


        

        if (tenureMonths == null ||
                tenureMonths <= 0) {

            throw new IllegalArgumentException(
                    "Loan tenure is not available");
        }


        

        for (int i = 1;
             i <= tenureMonths;
             i++) {

            EMI emi = new EMI();

            emi.setInstallmentNumber(i);

            emi.setAmount(emiAmount);

            emi.setPaidAmount(0.0);

            emi.setRemainingAmount(emiAmount);

            emi.setPaymentStatus("PENDING");

            emi.setLoanApplication(loan);

            emiRepository.save(emi);
        }


        

        List<EMI> emis =
                emiRepository
                        .findByLoanApplicationOrderByInstallmentNumberAsc(
                                loan);


       

        return emis
                .stream()
                .map(EMIMapper::toResponse)
                .collect(Collectors.toList());
    }


   
    @Override
    public EMIResponse getEMIById(
            UUID emiId) {

        EMI emi =
                emiRepository
                        .findById(emiId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "EMI not found with ID: "
                                                + emiId));

        return EMIMapper.toResponse(emi);
    }


   
    @Override
    @Transactional
    public EMIResponse payEMI(
            UUID emiId,
            EMIRequestdto request) {


        

        EMI emi =
                emiRepository
                        .findById(emiId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "EMI not found with ID: "
                                                + emiId));


      

        if (request == null ||
                request.getPaymentAmount() == null) {

            throw new IllegalArgumentException(
                    "Payment amount is required");
        }


       

        double paymentAmount =
                request.getPaymentAmount();


       
        if (paymentAmount <= 0) {

            throw new IllegalArgumentException(
                    "Payment amount must be greater than zero");
        }


       

        if ("PAID".equalsIgnoreCase(
                emi.getPaymentStatus())) {

            throw new IllegalArgumentException(
                    "This EMI is already fully paid");
        }


        Integer currentInstallment =
                emi.getInstallmentNumber();

        if (currentInstallment > 1) {

            EMI previousEMI =
                    emiRepository
                            .findByLoanApplicationAndInstallmentNumber(
                                    emi.getLoanApplication(),
                                    currentInstallment - 1);

            if (previousEMI != null &&
                    !"PAID".equalsIgnoreCase(
                            previousEMI.getPaymentStatus())) {

                throw new IllegalArgumentException(
                        "Please complete installment "
                                + (currentInstallment - 1)
                                + " before paying installment "
                                + currentInstallment);
            }
        }


       
        double remainingAmount =
                emi.getRemainingAmount();


        
        if (paymentAmount >
                remainingAmount) {

            throw new IllegalArgumentException(
                    "Payment amount cannot be greater than remaining EMI amount. "
                            + "Remaining amount is: "
                            + remainingAmount);
        }


        
        double newPaidAmount =
                emi.getPaidAmount()
                        + paymentAmount;


       
        double newRemainingAmount =
                emi.getAmount()
                        - newPaidAmount;


        

        newPaidAmount =
                round(newPaidAmount);

        newRemainingAmount =
                round(newRemainingAmount);


        

        if (newRemainingAmount < 0) {

            newRemainingAmount = 0;
        }


        
        emi.setPaidAmount(
                newPaidAmount);

        emi.setRemainingAmount(
                newRemainingAmount);


       
        if (newRemainingAmount == 0) {

            emi.setPaymentStatus(
                    "PAID");

        } else {

            emi.setPaymentStatus(
                    "PARTIAL");
        }


       

        EMI savedEMI =
                emiRepository.save(emi);


        return EMIMapper.toResponse(
                savedEMI);
    }


    
    @Override
    public List<EMIResponse> getEMIsByLoanId(
            Long loanApplicationId) {


       

        LoanApplication loan =
                loanApplicationRepository
                        .findById(loanApplicationId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Loan Application not found with ID: "
                                                + loanApplicationId));


        

        return emiRepository
                .findByLoanApplicationOrderByInstallmentNumberAsc(
                        loan)
                .stream()
                .map(EMIMapper::toResponse)
                .collect(Collectors.toList());
    }


    

    private double round(double value) {

        return Math.round(
                value * 100.0) / 100.0;
    }
}