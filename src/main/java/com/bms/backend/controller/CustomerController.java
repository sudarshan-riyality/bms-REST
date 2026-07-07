package com.bms.backend.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bms.backend.dto.CustomerRequestDto;
import com.bms.backend.dto.CustomerResponseDto;
import com.bms.backend.service.CustomerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    
    @PostMapping
    public ResponseEntity<CustomerResponseDto> saveCustomer(
           @Valid @RequestBody CustomerRequestDto dto) {
    																													
        return ResponseEntity.ok(
                customerService.saveCustomer(dto));
    }	

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> getCustomer(
            @PathVariable UUID id) {

        return ResponseEntity.ok(
                customerService.getCustomerById(id));
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseDto>>
    getAllCustomers() {

        return ResponseEntity.ok(
                customerService.getAllCustomers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDto>
    updateCustomer(
            @PathVariable UUID id,
            @RequestBody CustomerRequestDto dto) {

        return ResponseEntity.ok(
                customerService.updateCustomer(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(
            @PathVariable UUID id) {

        return ResponseEntity.ok(
                customerService.deleteCustomer(id));
    }
}