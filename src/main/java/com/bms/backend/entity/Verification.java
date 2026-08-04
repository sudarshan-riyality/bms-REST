package com.bms.backend.entity;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "verification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Verification {

    @Id
    @UuidGenerator
    private UUID verificationId;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private VerificationStatus status;

    private String remarks;

    private LocalDate verificationDate;
}