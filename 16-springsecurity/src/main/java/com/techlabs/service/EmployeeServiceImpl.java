package com.techlabs.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.REPO.ClientRepository;
import com.techlabs.REPO.EmployeeRepository;
import com.techlabs.dto.EmployeeDto;
import com.techlabs.entity.Client;
import com.techlabs.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ClientRepository clientRepository;

	private EmployeeDto convertToDto(Employee employee) {
		return new EmployeeDto(employee.getEmployeeId(), employee.getFirstname(), employee.getLastname(),
				employee.getSalary(), employee.getAccountnumber(), employee.getEmail(),
				employee.getClient().getRegistrationNumber());
	}

	private Employee convertToEntity(EmployeeDto employeeDto, Client client) {
		Employee employee = new Employee();
		employee.setFirstname(employeeDto.getFirstname());
		employee.setLastname(employeeDto.getLastname());
		employee.setSalary(employeeDto.getSalary());
		employee.setAccountnumber(employeeDto.getAccountnumber());
		employee.setEmail(employeeDto.getEmail());
		employee.setClient(client);
		return employee;
	}

	@Override
	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
		Optional<Client> clientOpt = clientRepository.findById(employeeDto.getClientRegistrationNumber());
		if (!clientOpt.isPresent()) {
			throw new RuntimeException("Client not found");
		}

		Employee employee = convertToEntity(employeeDto, clientOpt.get());
		Employee savedEmployee = employeeRepository.save(employee);
		return convertToDto(savedEmployee);
	}

	@Override
	public List<EmployeeDto> getAllEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		return employees.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@Override
	public Optional<EmployeeDto> getEmployeeById(int employeeId) {
		Optional<Employee> employeeOpt = employeeRepository.findById(employeeId);
		return employeeOpt.map(this::convertToDto);
	}

	@Override
	public EmployeeDto updateEmployee(int employeeId, EmployeeDto employeeDto) {
		Optional<Employee> employeeOpt = employeeRepository.findById(employeeId);
		if (!employeeOpt.isPresent()) {
			throw new RuntimeException("Employee not found");
		}

		Optional<Client> clientOpt = clientRepository.findById(employeeDto.getClientRegistrationNumber());
		if (!clientOpt.isPresent()) {
			throw new RuntimeException("Client not found");
		}

		Employee employee = convertToEntity(employeeDto, clientOpt.get());
		employee.setEmployeeId(employeeId); // Ensure the correct ID is set
		Employee updatedEmployee = employeeRepository.save(employee);
		return convertToDto(updatedEmployee);
	}

	@Override
	public void deleteEmployee(int employeeId) {
		employeeRepository.deleteById(employeeId);
	}
}
