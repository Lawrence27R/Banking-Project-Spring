package com.techlabs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.dto.TransactionRequestDto;
import com.techlabs.service.TransactionRequestService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/app/client")
public class TransactionRequestController {

    @Autowired
    private TransactionRequestService transactionRequestService;

    @PostMapping("/add-transaction-request")
    public ResponseEntity<String> addTransactionRequest(@Valid @RequestBody TransactionRequestDto requestDto) {
        transactionRequestService.addTransactionRequest(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Transaction request submitted successfully");
    }
}

