package com.techlabs.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientProfileDto {

    @NotNull(message = "Registration number is mandatory")
    private Long registrationNumber;

    @NotBlank(message = "First name is mandatory")
    private String firstname;

    @NotBlank(message = "Last name is mandatory")
    private String lastname;

    @NotBlank(message = "State is mandatory")
    private String state;

    @NotBlank(message = "City is mandatory")
    private String city;

    @NotNull(message = "Account number is mandatory")
    @Positive(message = "Account number must be positive")
    private Long accountNumber;

    @NotNull(message = "Balance is mandatory")
    @Positive(message = "Balance must be positive")
    private Double balance;
}

