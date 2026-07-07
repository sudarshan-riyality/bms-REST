package com.bms.backend.entity;

import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue
    private UUID customerId;

    private String customerName;

    private String email;

    private String phoneNumber;

    private String panNumber;

    private String aadhaarNumber;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Address address;
}