package com.techlabs.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "clients")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Client {

	@Id
	@Column(name = "registrationNumber")
	@NotNull(message = "Registration number is mandatory")
	private Long registrationNumber;

	@Column(name = "firstname")
	@NotBlank(message = "First name is mandatory")
	private String firstname;

	@Column(name = "lastname")
	@NotBlank(message = "Last name is mandatory")
	private String lastname;

	@Column(name = "kycStatus")
	@NotNull(message = "Status is mandatory")
	@Enumerated(EnumType.STRING)
	private ClientStatus kycStatus;

	@NotBlank(message = "State is mandatory")
	private String state;

	@Column(name = "account_number")
	private Long accountNumber;

	@NotBlank(message = "City is mandatory")
	private String city;

	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Employee> employees = new ArrayList<>(); // List of employees

	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference // Prevents infinite recursion during serialization
	private List<Transaction> transactions = new ArrayList<>(); // List of transactions associated with the client

	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Beneficiary> beneficiaries = new ArrayList<>(); // List of beneficiaries associated with the client

	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Document> documents = new ArrayList<>(); // List of documents uploaded by the client

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER) // Adjusted relationship
	@JoinColumn(name = "user_id", nullable = false)
	@JsonBackReference
	private User user;

	@OneToOne(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonBackReference
	private Bank bank; // One-to-one relationship with Bank

}
