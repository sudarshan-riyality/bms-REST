package com.bms.backend.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.*;

import com.bms.backend.dto.EMIRequestdto;
import com.bms.backend.dto.EMIResponse;
import com.bms.backend.service.EMIService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/emis")
@RequiredArgsConstructor
public class EMIController {

    private final EMIService emiService;


    
    @PostMapping("/generate/{loanApplicationId}")
    public List<EMIResponse> generateEMIs(
            @PathVariable Long loanApplicationId) {

        return emiService.generateEMIs(
                loanApplicationId);
    }


    @GetMapping("/{emiId}")
    public EMIResponse getEMIById(
            @PathVariable UUID emiId) {

        return emiService.getEMIById(
                emiId);
    }


    
    @PostMapping("/{emiId}/pay")
    public EMIResponse payEMI(
            @PathVariable UUID emiId,
            @RequestBody EMIRequestdto request) {

        return emiService.payEMI(
                emiId,
                request);
    }


    @GetMapping("/loan/{loanApplicationId}")
    public List<EMIResponse> getEMIsByLoanId(
            @PathVariable Long loanApplicationId) {

        return emiService.getEMIsByLoanId(
                loanApplicationId);
    }
}