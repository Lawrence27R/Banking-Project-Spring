package com.techlabs.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "banks")
public class Bank {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bankId;

	@NotBlank(message = "Bank name is mandatory")
	private String bankName;

	@NotBlank(message = "IFSC code is mandatory")
	private String ifscCode;


	@JsonManagedReference
	@OneToOne
	@JoinColumn(name = "registration_number", nullable = false, unique = true)
	private Client client;
}
