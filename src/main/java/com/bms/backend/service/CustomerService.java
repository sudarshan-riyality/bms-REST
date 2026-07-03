package com.bms.backend.service;

import java.util.List;
import java.util.UUID;

import com.bms.backend.dto.CustomerRequestDto;
import com.bms.backend.dto.CustomerResponseDto;

public interface CustomerService {

    CustomerResponseDto saveCustomer(CustomerRequestDto dto);

    CustomerResponseDto getCustomerById(UUID customerId);

    List<CustomerResponseDto> getAllCustomers();

    CustomerResponseDto updateCustomer(
            UUID customerId,
            CustomerRequestDto dto);

    String deleteCustomer(UUID customerId);
}