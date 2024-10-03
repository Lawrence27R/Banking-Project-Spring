package com.techlabs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.REPO.TransactionRequestRepository;
import com.techlabs.entity.TransactionRequest;
import com.techlabs.entity.TransactionRequestType;

@Service
public class AdminTransactionServiceImpl implements AdminTransactionService{

    @Autowired
    private TransactionRequestRepository transactionRequestRepository;

    @Override
    public List<TransactionRequest> getAllTransactionRequests() {
        return transactionRequestRepository.findAll();
    }

    @Override
    public List<Object> getTransactionDetails() {
        List<TransactionRequest> requests = getAllTransactionRequests();
        List<Object> details = new ArrayList<>();

        for (TransactionRequest request : requests) {
            if (request.getTransactionType() == TransactionRequestType.BENEFICIARY_PAYMENT) {
                details.add(request.getBeneficiaryPayment());
            } else if (request.getTransactionType() == TransactionRequestType.SALARY_DISBURSEMENT) {
                details.add(request.getSalaryDisbursement());
            }
        }

        return details;
    }
}


