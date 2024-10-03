package com.techlabs.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.techlabs.entity.Document;

public interface DocumentService {
	
	Document uploadKycDocument(MultipartFile file, String type, Long registrationNumber) throws java.io.IOException;

	List<Document> getCustomerKycDocuments(Long registrationNumber);

	Document getDocumentById(int documentId);
	
	List<Document> getDocumentsByClientRegistrationNumber(Long registrationNumber);

}