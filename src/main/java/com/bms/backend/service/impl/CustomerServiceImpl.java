package com.bms.backend.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bms.backend.dto.CustomerRequestDto;
import com.bms.backend.dto.CustomerResponseDto;
import com.bms.backend.entity.Customer;
import com.bms.backend.mapper.CustomerMapper;
import com.bms.backend.repository.CustomerRepository;
import com.bms.backend.service.CustomerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponseDto saveCustomer(CustomerRequestDto dto) {

      Customer customer = CustomerMapper.toEntity(dto);

        Customer saved = customerRepository.save(customer);

        return CustomerMapper.toDto(saved);
    }

    @Override
    public CustomerResponseDto getCustomerById(UUID customerId) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));

        return CustomerMapper.toDto(customer);
    }

    @Override
    public List<CustomerResponseDto> getAllCustomers() {

        return customerRepository.findAll()
                .stream()
                .map(CustomerMapper::toDto)
                .toList();
    }

    @Override
    public CustomerResponseDto updateCustomer(
            UUID customerId,
            CustomerRequestDto dto) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));

        customer.setCustomerName(dto.getCustomerName());
        customer.setEmail(dto.getEmail());
        customer.setPhoneNumber(dto.getPhoneNumber());
        customer.setPanNumber(dto.getPanNumber());
        customer.setAadhaarNumber(dto.getAadhaarNumber());

        if (customer.getAddress() != null &&
            dto.getAddress() != null) {

            customer.getAddress()
                    .setStreet(dto.getAddress().getStreet());

            customer.getAddress()
                    .setCity(dto.getAddress().getCity());

            customer.getAddress()
                    .setState(dto.getAddress().getState());

            customer.getAddress()
                    .setPincode(dto.getAddress().getPincode());
        }

        Customer updated = customerRepository.save(customer);

        return CustomerMapper.toDto(updated);
    }

    @Override
    public String deleteCustomer(UUID customerId) {

        customerRepository.deleteById(customerId);

        return "Customer deleted successfully";
    }
}