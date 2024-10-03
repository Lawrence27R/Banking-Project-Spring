package com.techlabs.service;

import java.util.List;
import java.util.Optional;

import com.techlabs.dto.EmployeeDto;

public interface EmployeeService {

	// Create or Update Employee using DTO
	EmployeeDto saveEmployee(EmployeeDto employeeDto);

	// Get All Employees
	List<EmployeeDto> getAllEmployees();

	// Get Employee by ID
	Optional<EmployeeDto> getEmployeeById(int employeeId);

	// Update Employee details using DTO
	EmployeeDto updateEmployee(int employeeId, EmployeeDto employeeDto);

	// Delete Employee by ID
	void deleteEmployee(int employeeId);
}
