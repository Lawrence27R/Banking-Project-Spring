package com.techlabs.REPO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techlabs.entity.Client;
import com.techlabs.entity.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {
	List<Document> findByClient(Client client);

	List<Document> findByClientRegistrationNumber(Long registrationNumber);
}

