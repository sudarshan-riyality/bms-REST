package com.bms.backend.mapper;

import com.bms.backend.dto.*;
import com.bms.backend.entity.*;

public class CustomerMapper {

    public static Customer toEntity(CustomerRequestDto dto) {

        Customer customer = new Customer();

        customer.setCustomerName(dto.getCustomerName());
        customer.setEmail(dto.getEmail());
        customer.setPhoneNumber(dto.getPhoneNumber());
        customer.setPanNumber(dto.getPanNumber());
        customer.setAadhaarNumber(dto.getAadhaarNumber());

        if (dto.getAddress() != null) {

            Address address = new Address();

            address.setStreet(dto.getAddress().getStreet());
            address.setCity(dto.getAddress().getCity());
            address.setState(dto.getAddress().getState());
            address.setPincode(dto.getAddress().getPincode());

            address.setCustomer(customer);
            customer.setAddress(address);
        }

        return customer;
    }

    public static CustomerResponseDto toDto(Customer customer) {

        CustomerResponseDto dto = new CustomerResponseDto();

        dto.setCustomerId(customer.getCustomerId());
        dto.setCustomerName(customer.getCustomerName());
        dto.setEmail(customer.getEmail());
        dto.setPhoneNumber(customer.getPhoneNumber());
        dto.setPanNumber(customer.getPanNumber());
        dto.setAadhaarNumber(customer.getAadhaarNumber());

        if (customer.getAddress() != null) {

            AddressResponseDto addressDto = new AddressResponseDto();

            addressDto.setAddressId(customer.getAddress().getAddressId());
            addressDto.setStreet(customer.getAddress().getStreet());
            addressDto.setCity(customer.getAddress().getCity());
            addressDto.setState(customer.getAddress().getState());
            addressDto.setPincode(customer.getAddress().getPincode());

            dto.setAddress(addressDto);
        }

        return dto;
    }
}