package com.techlabs.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.techlabs.dto.BeneficiaryDTO;

public interface BeneficiaryService {

	Page<BeneficiaryDTO> getAllBeneficiaries(Pageable pageable);

	Optional<BeneficiaryDTO> getBeneficiaryById(Integer beneficiaryId);

	BeneficiaryDTO createBeneficiary(BeneficiaryDTO beneficiaryDTO);

	BeneficiaryDTO updateBeneficiary(Integer beneficiaryId, BeneficiaryDTO beneficiaryDTO);

	void deleteBeneficiary(Integer beneficiaryId);

	
}