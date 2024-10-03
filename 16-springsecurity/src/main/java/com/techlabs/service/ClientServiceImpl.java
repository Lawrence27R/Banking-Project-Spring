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
    public Client updateClientProfile(Long registrationNumber, ClientProfileDto clientProfileDto) {
        Client existingClient = clientRepository.findById(registrationNumber)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        // Update profile fields
        existingClient.setFirstname(clientProfileDto.getFirstname());
        existingClient.setLastname(clientProfileDto.getLastname());
        existingClient.setState(clientProfileDto.getState());
        existingClient.setCity(clientProfileDto.getCity());
        existingClient.setAccountNumber(clientProfileDto.getAccountNumber()); // Add accountNumber


        return clientRepository.save(existingClient);
    }


    @Override
    public Document uploadDocument(Long registrationNumber, Document document) {
        Client client = clientRepository.findById(registrationNumber)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        document.setClient(client); // Set the back reference
        return documentRepository.save(document); // Save the document
    }
    

    @Override
    public Client registerClient(ClientProfileDto clientDto) {
        // Create a new Client entity from ClientProfileDto
        Client client = new Client();
        client.setRegistrationNumber(clientDto.getRegistrationNumber());
        client.setFirstname(clientDto.getFirstname());
        client.setLastname(clientDto.getLastname());
        client.setState(clientDto.getState());
        client.setCity(clientDto.getCity());
        client.setAccountNumber(clientDto.getAccountNumber()); // Add accountNumber
        client.setKycStatus(ClientStatus.PENDING); // Set default KYC status

        return clientRepository.save(client); // Save to the database
    }
    
    @Override
    public List<Client> getClientsByKycStatus() {
        return clientRepository.findByKycStatus(ClientStatus.PENDING);
    }
    
    @Override
    public Client updateClientKycStatus(Long registrationNumber, ClientStatus status) {
        Client client = clientRepository.findById(registrationNumber)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        client.setKycStatus(status); // Update KYC status (APPROVED or REJECTED)
        return clientRepository.save(client);
    }
}
