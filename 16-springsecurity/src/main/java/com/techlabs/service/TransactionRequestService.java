package com.techlabs.service;

import com.techlabs.dto.TransactionRequestDto;
import com.techlabs.entity.TransactionRequest;

public interface TransactionRequestService {
    TransactionRequest addTransactionRequest(TransactionRequestDto dto);
}

