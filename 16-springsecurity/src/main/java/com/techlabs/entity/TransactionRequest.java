package com.techlabs.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "transaction_requests")
public class TransactionRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Bank name is mandatory")
    private String bankName;

    @NotBlank(message = "IFSC code is mandatory")
    private String ifscCode;

    @NotNull(message = "Account number is mandatory")
    private Long accountNumber;

    @NotNull(message = "Transaction type is mandatory")
    @Enumerated(EnumType.STRING)
    private TransactionRequestType transactionType;

    @NotNull(message = "Transaction status is mandatory")
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToOne(mappedBy = "transactionRequest", cascade = CascadeType.ALL)
    private BeneficiaryPayment beneficiaryPayment;

    @OneToOne(mappedBy = "transactionRequest", cascade = CascadeType.ALL)
    private SalaryDisbursement salaryDisbursement;
}


