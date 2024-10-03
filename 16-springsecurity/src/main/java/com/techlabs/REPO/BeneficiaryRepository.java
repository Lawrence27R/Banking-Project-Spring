package com.techlabs.REPO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techlabs.entity.Beneficiary;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Integer> {

	Optional<Beneficiary> findByBeneficiaryAccountNumber(Long beneficiaryAccountNumber);
}