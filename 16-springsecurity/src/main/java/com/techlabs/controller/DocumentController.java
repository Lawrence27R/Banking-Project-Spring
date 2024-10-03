package com.techlabs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.techlabs.entity.Document;
import com.techlabs.service.DocumentService;

@RestController
@RequestMapping("/app/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    // Upload KYC document
    @PostMapping("/upload")
    public ResponseEntity<Document> uploadKycDocument(
            @RequestParam("file") MultipartFile file,
            @RequestParam("type") String type,
            @RequestParam("registrationNumber") Long registrationNumber) {
        try {
            Document document = documentService.uploadKycDocument(file, type, registrationNumber);
            return new ResponseEntity<>(document, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get documents for a specific client
    @GetMapping("/client/{registrationNumber}")
    public ResponseEntity<List<Document>> getCustomerKycDocuments(
            @PathVariable Long registrationNumber) {
        try {
            List<Document> documents = documentService.getCustomerKycDocuments(registrationNumber);
            return new ResponseEntity<>(documents, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Get document by ID
    @GetMapping("/document/{documentId}")
    public ResponseEntity<Document> getDocumentById(@PathVariable int documentId) {
        try {
            Document document = documentService.getDocumentById(documentId);
            return new ResponseEntity<>(document, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/client/documents/{registrationNumber}")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    public ResponseEntity<List<Document>> getDocumentsByClientRegistrationNumber(
            @PathVariable Long registrationNumber) {
        List<Document> documents = documentService.getDocumentsByClientRegistrationNumber(registrationNumber);
        if (documents.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }
}
