package com.bms.backend.entity;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "emi")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EMI {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID emiId;

   
    @Column(nullable = false)
    private Integer installmentNumber;

   
    @Column(nullable = false)
    private Double amount;

    
    @Column(nullable = false)
    private Double paidAmount;

    
    @Column(nullable = false)
    private Double remainingAmount;

    
    @Column(nullable = false)
    private String paymentStatus;

    @ManyToOne
    @JoinColumn(
            name = "loan_application_id",
            nullable = false
    )
    private LoanApplication loanApplication;
}