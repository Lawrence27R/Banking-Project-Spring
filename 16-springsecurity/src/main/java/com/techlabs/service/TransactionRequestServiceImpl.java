package com.techlabs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.REPO.BeneficiaryPaymentRepository;
import com.techlabs.REPO.ClientRepository;
import com.techlabs.REPO.SalaryDisbursementRepository;
import com.techlabs.REPO.TransactionRequestRepository;
import com.techlabs.dto.TransactionRequestDto;
import com.techlabs.entity.BeneficiaryPayment;
import com.techlabs.entity.Client;
import com.techlabs.entity.SalaryDisbursement;
import com.techlabs.entity.TransactionRequest;
import com.techlabs.entity.TransactionRequestType;
import com.techlabs.entity.TransactionStatus;
import com.techlabs.exceptions.ResourceNotFoundException;

@Service
public class TransactionRequestServiceImpl implements TransactionRequestService {

    @Autowired
    private TransactionRequestRepository transactionRequestRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BeneficiaryPaymentRepository beneficiaryPaymentRepository;

    @Autowired
    private SalaryDisbursementRepository salaryDisbursementRepository;

    @Override
    public TransactionRequest addTransactionRequest(TransactionRequestDto dto) {
        Client client = clientRepository.findByRegistrationNumber(dto.getRegistrationNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));

        TransactionRequest request = new TransactionRequest();
        request.setClient(client);
        request.setBankName(dto.getBankName());
        request.setIfscCode(dto.getIfscCode());
        request.setAccountNumber(dto.getAccountNumber());
        request.setTransactionType(dto.getTransactionType());
        request.setTransactionStatus(TransactionStatus.PENDING);

        // Save the common transaction request
        transactionRequestRepository.save(request);

        // Handle Beneficiary Payment or Salary Disbursement
        if (dto.getTransactionType() == TransactionRequestType.BENEFICIARY_PAYMENT) {
            BeneficiaryPayment payment = new BeneficiaryPayment();
            payment.setTransactionRequest(request);
            payment.setAmount(dto.getAmount());
            payment.setBeneficiaryAccountNumber(dto.getBeneficiaryAccountNumber());
            payment.setBeneficiaryIfsc(dto.getBeneficiaryIfscCode());
            beneficiaryPaymentRepository.save(payment);
        } else if (dto.getTransactionType() == TransactionRequestType.SALARY_DISBURSEMENT) {
            SalaryDisbursement disbursement = new SalaryDisbursement();
            disbursement.setTransactionRequest(request);
            disbursement.setTotalSalaryAmount(dto.getAmount());
            disbursement.setEmployeeCsvFileLink(dto.getEmployeeCsvFileLink());
            salaryDisbursementRepository.save(disbursement);
        }

        return request;
    }
}



