package com.techlabs.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "beneficiary_payments")
public class BeneficiaryPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Amount is mandatory for Beneficiary Payment")
    private Double amount;

    @NotNull(message = "Beneficiary account number is required")
    private Long beneficiaryAccountNumber;

    @NotBlank(message = "Beneficiary IFSC code is required")
    private String beneficiaryIfsc;

    @OneToOne
    @JoinColumn(name = "transaction_request_id", nullable = false)
    private TransactionRequest transactionRequest;
}


