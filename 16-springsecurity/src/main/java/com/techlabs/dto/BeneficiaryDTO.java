package com.techlabs.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeneficiaryDTO {
	
	@NotNull(message = "Beneficiary ID is required")
	private int beneficiaryId; // Or Long beneficiaryId if you expect nullable
	
	@NotBlank(message = "Beneficiary name is required")
	private String beneficiaryName;
	
	@NotNull(message = "Beneficiary account number is required")
	private long beneficiaryAccountNumber;
	
	@NotBlank(message = "IFSC code is required")
	private String beneficiaryIfsc;
	
	@NotNull(message = "Beneficiary amount is required")
	@Positive(message = "Beneficiary amount must be positive")
	private double beneficiaryAmount;
	
	private Long registrationNumber; 
}
