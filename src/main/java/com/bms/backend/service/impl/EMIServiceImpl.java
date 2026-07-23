package com.bms.backend.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bms.backend.dto.EMIRequestdto;
import com.bms.backend.dto.EMIResponse;
import com.bms.backend.entity.EMI;
import com.bms.backend.entity.LoanApplication;
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
    public EMIResponse createEMI(EMIRequestdto request) {

        LoanApplication loanApplication =
                loanApplicationRepository
                        .findById(request.getLoanApplicationId())
                        .orElseThrow();

        EMI emi = EMIMapper.toEntity(request);

        emi.setLoanApplication(loanApplication);

        EMI savedEmi = emiRepository.save(emi);

        return EMIMapper.toResponse(savedEmi);
    }

    @Override
    public EMIResponse getEMIById(UUID emiId) {

        EMI emi = emiRepository
                .findById(emiId)
                .orElseThrow();

        return EMIMapper.toResponse(emi);
    }
}