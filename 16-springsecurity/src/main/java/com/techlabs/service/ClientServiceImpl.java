package com.techlabs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.REPO.ClientRepository;
import com.techlabs.REPO.DocumentRepository;
import com.techlabs.dto.ClientProfileDto; // Import ClientProfileDto
import com.techlabs.entity.Client;
import com.techlabs.entity.ClientStatus;
import com.techlabs.entity.Document;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ClientServiceImpl implements ClientService {
    
    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public Optional<Client> getClientById(Long registrationNumber) {
        return clientRepository.findById(registrationNumber);
    }

    @Override
    @Transactional // Ensure changes are committed to the database
    public Client updateClientProfile(Long registrationNumber, ClientProfileDto clientProfileDto) {
        Client existingClient = clientRepository.findById(registrationNumber)
                .orElseThrow(() -> new EntityNotFoundException("Client not found with registration number: " + registrationNumber));

        // Update profile fields
        existingClient.setFirstname(clientProfileDto.getFirstname());
        existingClient.setLastname(clientProfileDto.getLastname());
        existingClient.setState(clientProfileDto.getState());
        existingClient.setCity(clientProfileDto.getCity());
        existingClient.setAccountNumber(clientProfileDto.getAccountNumber());

        return clientRepository.save(existingClient);
    }

    @Override
    @Transactional // Ensure changes are committed to the database
    public Document uploadDocument(Long registrationNumber, Document document) {
        Client client = clientRepository.findById(registrationNumber)
                .orElseThrow(() -> new EntityNotFoundException("Client not found with registration number: " + registrationNumber));

        // Set the back reference
        document.setClient(client);
        
        // Optionally, validate document fields before saving
        validateDocument(document);

        return documentRepository.save(document);
    }
    
    @Override
    @Transactional // Ensure the registration is a single atomic operation
    public Client registerClient(ClientProfileDto clientDto) {
        // Log the incoming DTO
        System.out.println("Received ClientProfileDto: " + clientDto);
        
        // Create a new Client entity from ClientProfileDto
        Client client = new Client();
        client.setRegistrationNumber(clientDto.getRegistrationNumber());
        client.setFirstname(clientDto.getFirstname());
        client.setLastname(clientDto.getLastname());
        client.setState(clientDto.getState());
        client.setCity(clientDto.getCity());
        client.setAccountNumber(clientDto.getAccountNumber());
        client.setKycStatus(ClientStatus.PENDING);
        client.setBalance(clientDto.getBalance()); // Set the balance

        return clientRepository.save(client);
    }

    
    
    @Override
    public List<Client> getClientsByKycStatus(ClientStatus status) {
        return clientRepository.findByKycStatus(status);
    }
    
    @Override
    @Transactional // Ensure changes are committed to the database
    public Client updateClientKycStatus(Long registrationNumber, ClientStatus status) {
        Client client = clientRepository.findById(registrationNumber)
                .orElseThrow(() -> new EntityNotFoundException("Client not found with registration number: " + registrationNumber));

        client.setKycStatus(status); // Update KYC status
        return clientRepository.save(client);
    }

    private void validateDocument(Document document) {
        // Add document validation logic here if necessary
    }
}

