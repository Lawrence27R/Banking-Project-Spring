package com.techlabs.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class EmployeeDto {

    private int employeeId; // For updates or lookups

    @NotBlank(message = "First Name is mandatory")
    private String firstname;

    @NotBlank(message = "Last Name is mandatory")
    private String lastname;

    @NotNull(message = "Salary is mandatory")
    @Positive(message = "Salary must be positive")
    private Double salary;

    @NotNull(message = "Account number is mandatory")
    private Long accountnumber;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotNull(message = "Balance is mandatory")
    @Positive(message = "Balance must be positive")
    private double balance;  // Added balance field

    private Long clientRegistrationNumber;

    // Explicit constructor to match the one needed in EmployeeServiceImpl
    public EmployeeDto(int employeeId, String firstname, String lastname, Double salary, Long accountnumber, double balance, String email, Long clientRegistrationNumber) {
        this.employeeId = employeeId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.salary = salary;
        this.accountnumber = accountnumber;
        this.balance = balance;
        this.email = email;
        this.clientRegistrationNumber = clientRegistrationNumber;
    }
}


