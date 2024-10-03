package com.techlabs.service;

import java.util.List;

import com.techlabs.entity.TransactionRequest;

public interface AdminTransactionService {

	List<TransactionRequest> getAllTransactionRequests();

	List<Object> getTransactionDetails();
}
