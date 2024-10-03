package com.techlabs.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.REPO.BeneficiaryRepository;
import com.techlabs.REPO.ClientRepository;
import com.techlabs.dto.BeneficiaryDTO;
import com.techlabs.entity.Beneficiary;
import com.techlabs.entity.Client;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {

    @Autowired
    private BeneficiaryRepository beneficiaryRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Page<BeneficiaryDTO> getAllBeneficiaries(Pageable pageable) {
        Page<Beneficiary> beneficiaries = beneficiaryRepository.findAll(pageable);
        List<BeneficiaryDTO> beneficiaryDTOList = beneficiaries.stream()
            .map(this::toBeneficiaryDTO)
            .collect(Collectors.toList());
        return new PageImpl<>(beneficiaryDTOList, pageable, beneficiaries.getTotalElements());
    }

    @Override
    public Optional<BeneficiaryDTO> getBeneficiaryById(Integer beneficiaryId) {
        return beneficiaryRepository.findById(beneficiaryId)
            .map(this::toBeneficiaryDTO);
    }

    @Override
    public BeneficiaryDTO createBeneficiary(BeneficiaryDTO beneficiaryDTO) {
        // Check if client exists by registration number
        Client client = clientRepository.findByRegistrationNumber(beneficiaryDTO.getRegistrationNumber())
            .orElseThrow(() -> new RuntimeException("Client not found with registration number: " + beneficiaryDTO.getRegistrationNumber()));
        
        // Proceed with the creation logic
        Beneficiary beneficiary = toBeneficiary(beneficiaryDTO);
        beneficiary.setClient(client); // Set the client for the beneficiary
        Beneficiary savedBeneficiary = beneficiaryRepository.save(beneficiary);
        return toBeneficiaryDTO(savedBeneficiary);
    }

    @Override
    public BeneficiaryDTO updateBeneficiary(Integer beneficiaryId, BeneficiaryDTO beneficiaryDTO) {
        return beneficiaryRepository.findById(beneficiaryId).map(existingBeneficiary -> {
            // Update fields
            existingBeneficiary.setBeneficiaryName(beneficiaryDTO.getBeneficiaryName());
            existingBeneficiary.setBeneficiaryAccountNumber(beneficiaryDTO.getBeneficiaryAccountNumber());
            existingBeneficiary.setBeneficiaryIfsc(beneficiaryDTO.getBeneficiaryIfsc());
            existingBeneficiary.setBalance(beneficiaryDTO.getBalance()); // Change to balance

            // Find and set the client
            Client client = clientRepository.findByRegistrationNumber(beneficiaryDTO.getRegistrationNumber())
                .orElseThrow(() -> new RuntimeException("Client not found with registration number: " + beneficiaryDTO.getRegistrationNumber()));
            existingBeneficiary.setClient(client);

            Beneficiary updatedBeneficiary = beneficiaryRepository.save(existingBeneficiary);
            return toBeneficiaryDTO(updatedBeneficiary);
        }).orElseThrow(() -> new RuntimeException("Beneficiary with id " + beneficiaryId + " not found"));
    }

    @Override
    public void deleteBeneficiary(Integer beneficiaryId) {
        if (beneficiaryRepository.existsById(beneficiaryId)) {
            beneficiaryRepository.deleteById(beneficiaryId);
        } else {
            throw new RuntimeException("Beneficiary with id " + beneficiaryId + " not found");
        }
    }

    // Conversion methods
    private BeneficiaryDTO toBeneficiaryDTO(Beneficiary beneficiary) {
        return new BeneficiaryDTO(
            beneficiary.getBeneficiaryId(), 
            beneficiary.getBeneficiaryName(),
            beneficiary.getBeneficiaryAccountNumber(), 
            beneficiary.getBeneficiaryIfsc(),
            beneficiary.getBalance(), // Change to balance
            beneficiary.getClient().getRegistrationNumber() // Use client's registration number
        );
    }

    private Beneficiary toBeneficiary(BeneficiaryDTO beneficiaryDTO) {
        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setBeneficiaryId(beneficiaryDTO.getBeneficiaryId());
        beneficiary.setBeneficiaryName(beneficiaryDTO.getBeneficiaryName());
        beneficiary.setBeneficiaryAccountNumber(beneficiaryDTO.getBeneficiaryAccountNumber());
        beneficiary.setBeneficiaryIfsc(beneficiaryDTO.getBeneficiaryIfsc());
        beneficiary.setBalance(beneficiaryDTO.getBalance()); // Change to balance

        // Fetch client by registration number and associate it
        Client client = clientRepository.findByRegistrationNumber(beneficiaryDTO.getRegistrationNumber())
            .orElseThrow(() -> new RuntimeException("Client not found with registration number: " + beneficiaryDTO.getRegistrationNumber()));
        beneficiary.setClient(client);

        return beneficiary;
    }
}

