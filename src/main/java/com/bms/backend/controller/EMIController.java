package com.bms.backend.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bms.backend.dto.EMIRequestdto;
import com.bms.backend.dto.EMIResponse;
import com.bms.backend.service.EMIService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/emis")
@RequiredArgsConstructor
public class EMIController {

    private final EMIService emiService;

    @PostMapping
    public EMIResponse createEMI(@RequestBody EMIRequestdto request) {

        return emiService.createEMI(request);
    }

    @GetMapping("/{id}")
    public EMIResponse getEMIById(@PathVariable UUID id) {

        return emiService.getEMIById(id);
    }
}