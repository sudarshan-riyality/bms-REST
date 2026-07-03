package com.bms.backend.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDto {

    private UUID customerId;
    private String customerName;
    private String email;
    private String phoneNumber;
    private String panNumber;
    private String aadhaarNumber;

    private AddressResponseDto address;
}