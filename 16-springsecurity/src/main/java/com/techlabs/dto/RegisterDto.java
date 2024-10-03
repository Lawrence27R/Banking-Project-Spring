package com.techlabs.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class RegisterDto {
    private String username;
    private String email;
    private String password;
    
    @NotNull(message = "Registration number is mandatory")
    private Long registrationNumber; // Add this field
    
    @NotBlank(message = "First name is mandatory")
    private String firstname; // Add this field

    @NotBlank(message = "Last name is mandatory")
    private String lastname; // Add this field
}

