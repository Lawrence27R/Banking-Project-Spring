package com.techlabs.service;


import java.util.List;
import java.util.Optional;

import com.techlabs.dto.TransactionDTO;
import com.techlabs.entity.Transaction; 
 
public interface TransactionService { 
 
    Transaction createTransaction(TransactionDTO transactionDTO); // Change parameter type 
 
    Optional<Transaction> findTransactionById(Integer transactionId); 
 
    List<Transaction> findAllTransactions(); 
  
	List<Transaction> findByRegistrationNumber(long registrationNumber);

	List<Transaction> findByBeneficiaryAccountNumber(Long beneficiaryAccountNumber);

}