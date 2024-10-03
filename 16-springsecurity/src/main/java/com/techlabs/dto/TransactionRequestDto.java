package com.techlabs.dto;

import com.techlabs.entity.TransactionRequestType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestDto {

    @NotNull(message = "Client registration number is mandatory")
    private Long registrationNumber;

    @NotBlank(message = "Bank name is mandatory")
    private String bankName;

    @NotBlank(message = "IFSC code is mandatory")
    private String ifscCode;

    @NotNull(message = "Account number is mandatory")
    private Long accountNumber;

    @NotNull(message = "Transaction type is mandatory")
    private TransactionRequestType transactionType;

    // Optional for Salary Disbursement, required for Beneficiary Payment
    private Long beneficiaryAccountNumber;
    private String beneficiaryIfscCode;

    // Mandatory for Salary Disbursement, ignored for Beneficiary Payment
    private String employeeCsvFileLink;

    @NotNull(message = "Amount is mandatory")
    private Double amount;
}


