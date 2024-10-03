package com.techlabs.REPO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techlabs.entity.TransactionRequest;

@Repository
public interface TransactionRequestRepository extends JpaRepository<TransactionRequest, Long> {
}

