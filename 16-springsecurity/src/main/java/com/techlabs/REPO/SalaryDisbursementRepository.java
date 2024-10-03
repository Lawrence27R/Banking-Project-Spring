package com.techlabs.REPO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techlabs.entity.SalaryDisbursement;

@Repository
public interface SalaryDisbursementRepository extends JpaRepository<SalaryDisbursement, Long> {
}
