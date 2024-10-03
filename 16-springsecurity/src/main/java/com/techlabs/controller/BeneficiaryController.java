package com.techlabs.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.dto.BeneficiaryDTO;
import com.techlabs.service.BeneficiaryService;
@RestController
@RequestMapping("/app/beneficiary")
public class BeneficiaryController {

    @Autowired
    private BeneficiaryService beneficiaryService;

    // Get all beneficiaries with pagination
    @GetMapping
    public ResponseEntity<Page<BeneficiaryDTO>> getAllBeneficiaries(Pageable pageable) {
        Page<BeneficiaryDTO> beneficiaries = beneficiaryService.getAllBeneficiaries(pageable);
        return new ResponseEntity<>(beneficiaries, HttpStatus.OK);
    }

    // Get a specific beneficiary by ID
    @GetMapping("/{id}")
    public ResponseEntity<BeneficiaryDTO> getBeneficiaryById(@PathVariable Integer id) {
        Optional<BeneficiaryDTO> beneficiary = beneficiaryService.getBeneficiaryById(id);
        return beneficiary.map(ResponseEntity::ok)
                          .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Create a new beneficiary
    @PostMapping
    public ResponseEntity<BeneficiaryDTO> createBeneficiary(@RequestBody BeneficiaryDTO beneficiaryDTO) {
        try {
            BeneficiaryDTO createdBeneficiary = beneficiaryService.createBeneficiary(beneficiaryDTO);
            return new ResponseEntity<>(createdBeneficiary, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Update an existing beneficiary
    @PutMapping("/{id}")
    public ResponseEntity<BeneficiaryDTO> updateBeneficiary(@PathVariable Integer id, @RequestBody BeneficiaryDTO beneficiaryDTO) {
        try {
            BeneficiaryDTO updatedBeneficiary = beneficiaryService.updateBeneficiary(id, beneficiaryDTO);
            return new ResponseEntity<>(updatedBeneficiary, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a beneficiary
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBeneficiary(@PathVariable Integer id) {
        try {
            beneficiaryService.deleteBeneficiary(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}