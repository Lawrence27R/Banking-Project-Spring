package com.techlabs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankDto {

	private String bankName;

	private String ifscCode;

	private Long clientRegistrationNumber;
}