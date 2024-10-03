package com.techlabs.REPO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.entity.EmployeeTransaction;

public interface EmployeeTransactionRepository extends JpaRepository<EmployeeTransaction, Integer> {
    // Additional query methods can be defined here if needed
}
