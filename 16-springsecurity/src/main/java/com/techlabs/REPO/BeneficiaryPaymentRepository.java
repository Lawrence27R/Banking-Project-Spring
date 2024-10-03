package com.techlabs.REPO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techlabs.entity.BeneficiaryPayment;

@Repository
public interface BeneficiaryPaymentRepository extends JpaRepository<BeneficiaryPayment, Long> {
}

	