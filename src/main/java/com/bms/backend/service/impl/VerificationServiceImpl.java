package com.bms.backend.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bms.backend.dto.VerificationRequestDto;
import com.bms.backend.dto.VerificationResponseDto;
import com.bms.backend.entity.Customer;
import com.bms.backend.entity.Verification;
import com.bms.backend.exception.ResourceNotFoundException;
import com.bms.backend.mapper.VerificationMapper;
import com.bms.backend.repository.CustomerRepository;
import com.bms.backend.repository.VerificationRepository;
import com.bms.backend.service.VerificationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VerificationServiceImpl implements VerificationService {

	
	private final VerificationRepository verificationRepository;

    private final CustomerRepository customerRepository;
	
	
    @Override
    public VerificationResponseDto createVerification(
            UUID customerId,
            VerificationRequestDto dto) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Customer not found with ID: " + customerId));

        Verification verification =
                VerificationMapper.toEntity(dto, customer);

        Verification savedVerification =
                verificationRepository.save(verification);

        return VerificationMapper.toResponseDto(savedVerification);
    }

    @Override
    public VerificationResponseDto getVerificationById(
            UUID verificationId) {

        Verification verification = verificationRepository
                .findById(verificationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Verification not found with ID: " + verificationId));

        return VerificationMapper.toResponseDto(verification);
    }

    @Override
    public VerificationResponseDto getVerificationByCustomerId(
            UUID customerId) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Customer not found with ID: " + customerId));

        Verification verification = verificationRepository
                .findByCustomer(customer)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Verification not found for Customer ID: " + customerId));

        return VerificationMapper.toResponseDto(verification);
    }

    @Override
    public VerificationResponseDto updateVerification(
            UUID verificationId,
            VerificationRequestDto dto) {

        Verification verification = verificationRepository
                .findById(verificationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Verification not found with ID: " + verificationId));

        verification.setStatus(dto.getStatus());
        verification.setRemarks(dto.getRemarks());

        Verification updatedVerification =
                verificationRepository.save(verification);

        return VerificationMapper.toResponseDto(updatedVerification);
    }

    @Override
    public void deleteVerification(
            UUID verificationId) {

        Verification verification = verificationRepository
                .findById(verificationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Verification not found with ID: " + verificationId));

        verificationRepository.delete(verification);
    }
}