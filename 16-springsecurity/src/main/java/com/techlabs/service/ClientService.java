package com.techlabs.service;

import java.util.List;
import java.util.Optional;

import com.techlabs.dto.ClientProfileDto; // Import ClientProfileDto
import com.techlabs.entity.Client;
import com.techlabs.entity.ClientStatus;
import com.techlabs.entity.Document;

public interface ClientService {
    Optional<Client> getClientById(Long registrationNumber);
    
    Client updateClientProfile(Long registrationNumber, ClientProfileDto clientProfileDto);
    
    Document uploadDocument(Long registrationNumber, Document document);
    
    Client registerClient(ClientProfileDto clientDto); // Method for registering a new client
    
    List<Client> getClientsByKycStatus(ClientStatus status); // Accept status to filter clients
    
    Client updateClientKycStatus(Long registrationNumber, ClientStatus status); 
}

