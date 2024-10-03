package com.techlabs.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.dto.EmployeeDto;
import com.techlabs.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/app/employees") // Base URL for all employee-related endpoints
@Validated // Enables validation annotations
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	// Create a new employee
	@PostMapping("/add")
	public ResponseEntity<EmployeeDto> createEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
		EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto);
		return ResponseEntity.ok(savedEmployee);
	}

	// Get all employees
	@GetMapping
	public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
		List<EmployeeDto> employees = employeeService.getAllEmployees();
		return ResponseEntity.ok(employees);
	}

	// Get an employee by ID
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable int id) {
		Optional<EmployeeDto> employee = employeeService.getEmployeeById(id);
		return employee.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	// Update an employee by ID
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable int id,
			@Valid @RequestBody EmployeeDto employeeDto) {
		EmployeeDto updatedEmployee = employeeService.updateEmployee(id, employeeDto);
		return ResponseEntity.ok(updatedEmployee);
	}

	// Delete an employee by ID
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable int id) {
		employeeService.deleteEmployee(id);
		return ResponseEntity.noContent().build(); // No content response for successful deletion
	}
}
