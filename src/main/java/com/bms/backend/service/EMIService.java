package com.bms.backend.service;

import java.util.List;
import java.util.UUID;

import com.bms.backend.dto.EMIRequestdto;
import com.bms.backend.dto.EMIResponse;

public interface EMIService {

   
    List<EMIResponse> generateEMIs(
            Long loanApplicationId);

   
    EMIResponse getEMIById(
            UUID emiId);

    EMIResponse payEMI(
            UUID emiId,
            EMIRequestdto request);

  
    List<EMIResponse> getEMIsByLoanId(
            Long loanApplicationId);
}