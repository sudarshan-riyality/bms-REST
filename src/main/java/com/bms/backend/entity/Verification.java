package com.bms.backend.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name="verification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Verification {
	@Id
	@GeneratedValue
	private UUID verificationId;
	
	@ManyToOne
	@JoinColumn(name = "customer_id", nullable=false)
	private Customer customer;
	
	@Enumerated(EnumType.STRING)
	private DocumentType documentType;
	
	@Enumerated(EnumType.STRING)
	private VerificationStatus verificationstatus;
	
	@ManyToOne
	@JoinColumn(name="verified_by")
	private User verifiedBy;

}
