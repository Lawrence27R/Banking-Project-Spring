package com.techlabs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.dto.TransactionDTO;
import com.techlabs.entity.Transaction;
import com.techlabs.service.TransactionService;

@RestController
@RequestMapping("/app/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // Endpoint to create a new transaction
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        Transaction transaction = transactionService.createTransaction(transactionDTO);
        return ResponseEntity.ok(transaction);
    }

    // Endpoint to get transactions by beneficiary account number
    @GetMapping("/beneficiary/{beneficiaryAccountNumber}")
    public ResponseEntity<List<Transaction>> getTransactionsByBeneficiaryAccountNumber(
            @PathVariable Long beneficiaryAccountNumber) {
        List<Transaction> transactions = transactionService.findByBeneficiaryAccountNumber(beneficiaryAccountNumber);
        return ResponseEntity.ok(transactions);
    }

    // Endpoint to get transactions by registration number
    @GetMapping("/registration/{registrationNumber}")
    public ResponseEntity<List<Transaction>> getTransactionsByRegistrationNumber(
            @PathVariable Long registrationNumber) {
        List<Transaction> transactions = transactionService.findByRegistrationNumber(registrationNumber);
        return ResponseEntity.ok(transactions);
    }
}
