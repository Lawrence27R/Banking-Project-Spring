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
    private Integer beneficiaryId;  // Use Integer to match with the entity if it's not Long
    
    @NotBlank(message = "Beneficiary name is required")
    private String beneficiaryName;
    
    @NotNull(message = "Beneficiary account number is required")
    private Long beneficiaryAccountNumber;
    
    @NotBlank(message = "IFSC code is required")
    private String beneficiaryIfsc;
    
    @NotNull(message = "Beneficiary balance is required")
    @Positive(message = "Beneficiary balance must be positive")
    private Double balance;  // Consistent naming with the entity's `balance` field
    
    @NotNull(message = "Client registration number is required")
    private Long registrationNumber; // Client's registration number
}


