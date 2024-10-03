package com.techlabs.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction")
public class Transaction {
	@Id														// private Integer transactionId;
	@NotNull(message = "Sender account is mandatory")
	@Column(name = "senderAcct")
	private long senderAcct;

	@NotNull(message = "Receiver account is mandatory")
	@Column(name = "receiverAcct")
	private long receiverAcct;

	@NotNull(message = "Amount is mandatory")
	@Positive(message = "Amount must be positive")
	@Column(name = "amount")
	private Double amount;

	@NotNull(message = "Transaction type is mandatory")
	@Column(name = "transaction")
	@Enumerated(EnumType.STRING)
	private TransactionType transaction;

	@NotNull(message = "Timestamp is mandatory")
	@Column(name = "timestamp", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime timestamp;

	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "registration_number", nullable = false)
	private Client client; // Reference back to the Client entity

}