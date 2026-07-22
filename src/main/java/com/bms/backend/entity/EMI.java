package com.bms.backend.entity;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="emi")
@Getter
@Setter
@NoArgsConstructor
public class EMI {  
	@Id
	@UuidGenerator
	private UUID emiId;
	private Integer installmentNumber;
	private Double amount;
	private String paymentStatus;
	@ManyToOne
	@JoinColumn(name="loan_application_id")
	private LoanApplication loanApplication;
	
	
	

}
