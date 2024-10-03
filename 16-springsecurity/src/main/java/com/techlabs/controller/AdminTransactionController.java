package com.techlabs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.service.AdminTransactionService;

@RestController
@RequestMapping("/app/admin")
public class AdminTransactionController {

    @Autowired
    private AdminTransactionService adminTransactionService;

    @GetMapping("/transaction-requests")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    public ResponseEntity<List<Object>> getAllTransactionRequests() {
        List<Object> transactionDetails = adminTransactionService.getTransactionDetails();
        return ResponseEntity.ok(transactionDetails);
    }
}
