package com.techlabs.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor; 
 
@Entity 
@Table(name = "employees") 
@AllArgsConstructor 
@RequiredArgsConstructor 
@Data 
public class Employee { 
 
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    @Column(name = "employeeId") 
    private int employeeId; 
 
    @Column(name = "firstname") 
    @NotBlank(message = "First Name is mandatory") 
    private String firstname; 
 
    @Column(name = "lastname") 
    @NotBlank(message = "Last Name is mandatory") 
    private String lastname; 
 
    @Column(name = "salary") 
    @NotNull(message = "Salary is mandatory") 
    @Positive(message = "Salary must be positive") 
    private Double salary; 
 
    @NotNull(message = "Account number is mandatory") 
    private Long accountnumber; 
 
    @Email(message = "Email should be valid") 
    @NotBlank(message = "Email is mandatory") 
    private String email; 
 
    // Many employees belong to one client
    @JsonBackReference 
    @ManyToOne 
    @JoinColumn(name = "client_registrationNumber", nullable = false) // Foreign key column for Client 
    private Client client;  // Added relationship to client 
}