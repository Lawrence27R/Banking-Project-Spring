package com.techlabs.dto;

import lombok.Data;

@Data
public class TransactionDTO {
    private long beneficiaryAccountNumber; 
    private long clientAccountNumber; 
    private double amount; 
}
