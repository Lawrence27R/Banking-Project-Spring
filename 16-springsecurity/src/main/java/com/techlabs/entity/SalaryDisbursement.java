package com.techlabs.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "salary_disbursements")
public class SalaryDisbursement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Total salary amount is mandatory for Salary Disbursement")
    private Double totalSalaryAmount;

    private String employeeCsvFileLink;

    @OneToOne
    @JoinColumn(name = "transaction_request_id", nullable = false)
    private TransactionRequest transactionRequest;
}


