package com.bms.backend.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bms.backend.dto.VerificationRequestDto;
import com.bms.backend.dto.VerificationResponseDto;
import com.bms.backend.service.VerificationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/verifications")
@RequiredArgsConstructor
public class VerificationController {

    private final VerificationService verificationService;

    @PostMapping("/{customerId}")
    public ResponseEntity<VerificationResponseDto> createVerification(
            @PathVariable UUID customerId,
            @RequestBody VerificationRequestDto dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(verificationService.createVerification(customerId, dto));
    }

    @GetMapping("/{verificationId}")
    public ResponseEntity<VerificationResponseDto> getVerificationById(
            @PathVariable UUID verificationId) {

        return ResponseEntity.ok(
                verificationService.getVerificationById(verificationId));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<VerificationResponseDto> getVerificationByCustomerId(
            @PathVariable UUID customerId) {

        return ResponseEntity.ok(
                verificationService.getVerificationByCustomerId(customerId));
    }

    @PutMapping("/{verificationId}")
    public ResponseEntity<VerificationResponseDto> updateVerification(
            @PathVariable UUID verificationId,
            @RequestBody VerificationRequestDto dto) {

        return ResponseEntity.ok(
                verificationService.updateVerification(verificationId, dto));
    }

    @DeleteMapping("/{verificationId}")
    public ResponseEntity<String> deleteVerification(
            @PathVariable UUID verificationId) {

        verificationService.deleteVerification(verificationId);

        return ResponseEntity.ok("Verification Deleted Successfully");
    }
}