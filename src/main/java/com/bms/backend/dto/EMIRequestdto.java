package com.bms.backend.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EMIRequestdto {
	private  Integer installmentNumber;
	private Double amount;
	private String paymentStatus;
	private Long loanApplicationId;
	
	

}
