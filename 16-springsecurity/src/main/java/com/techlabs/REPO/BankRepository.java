package com.techlabs.REPO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.entity.Bank;

public interface BankRepository extends JpaRepository<Bank, Integer> {

//	Bank findByAccountNumber(Long accountNumber);
	// Additional query methods if needed
}
