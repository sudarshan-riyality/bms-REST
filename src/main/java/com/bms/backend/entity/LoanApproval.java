package com.bms.backend.entity;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "loan_approval")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanApproval {

    @Id
    @UuidGenerator
    private UUID approvalId;

    @OneToOne
    @JoinColumn(name = "loan_application_id")
    private LoanApplication loanApplication;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus status;

    private String remarks;

    private LocalDate approvalDate;
}