package com.techlabs.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // This generates getters, setters, and other utility methods
@AllArgsConstructor
@NoArgsConstructor // Add this if you want a no-args constructor
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

    @Column(name = "account_number", unique = true, nullable = false)
	private Long accountNumber;
    
}
