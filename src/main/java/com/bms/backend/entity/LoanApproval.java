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
    @JoinColumn(
            name = "loan_application_id",
            nullable = false,
            unique = true
    )
    private LoanApplication loanApplication;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus status;

    private String remarks;

    private LocalDate approvalDate;
}