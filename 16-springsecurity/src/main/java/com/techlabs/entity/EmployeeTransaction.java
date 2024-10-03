package com.techlabs.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "emp_transactions")
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeTransaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically generate employeeId
    @Column(name = "emp_id")
    private int employeeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "salary")
    private double salary;

    @Column(name = "balance")
    private double balance;
}




