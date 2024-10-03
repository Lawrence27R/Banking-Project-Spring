package com.techlabs.REPO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techlabs.entity.Transaction;
import com.techlabs.entity.TransactionType;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    // Ensure the parameter types are correct
    List<Transaction> findBySenderAcct(Long senderAcct); 
    List<Transaction> findByReceiverAcct(Long receiverAcct); 
    List<Transaction> findByTransaction(TransactionType transactionType);
}
