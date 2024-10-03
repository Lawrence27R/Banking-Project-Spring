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
import com.techlabs.exceptions.ClientNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional // Ensures transaction management
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ClientRepository clientRepository;

    private EmployeeDto convertToDto(Employee employee) {
        return new EmployeeDto(
                employee.getEmployeeId(),
                employee.getFirstname(),
                employee.getLastname(),
                employee.getSalary(),
                employee.getAccountnumber(),
                employee.getBalance(), // Include balance in DTO conversion
                employee.getEmail(),
                employee.getClient().getRegistrationNumber()
        );
    }

    private Employee convertToEntity(EmployeeDto employeeDto, Client client) {
        Employee employee = new Employee();
        employee.setFirstname(employeeDto.getFirstname());
        employee.setLastname(employeeDto.getLastname());
        employee.setSalary(employeeDto.getSalary());
        employee.setAccountnumber(employeeDto.getAccountnumber());
        employee.setBalance(employeeDto.getBalance()); // Include balance from DTO
        employee.setEmail(employeeDto.getEmail());
        employee.setClient(client);
        return employee;
    }

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Client client = clientRepository.findById(employeeDto.getClientRegistrationNumber())
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));

        Employee employee = convertToEntity(employeeDto, client);
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
        return employeeRepository.findById(employeeId).map(this::convertToDto);
    }

    @Override
    public EmployeeDto updateEmployee(int employeeId, EmployeeDto employeeDto) {
        Employee existingEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        Client client = clientRepository.findById(employeeDto.getClientRegistrationNumber())
        		.orElseThrow(() -> new ClientNotFoundException("Client not found"));


        // Update existing employee fields
        existingEmployee.setFirstname(employeeDto.getFirstname());
        existingEmployee.setLastname(employeeDto.getLastname());
        existingEmployee.setSalary(employeeDto.getSalary());
        existingEmployee.setAccountnumber(employeeDto.getAccountnumber());
        existingEmployee.setBalance(employeeDto.getBalance()); // Update balance
        existingEmployee.setEmail(employeeDto.getEmail());
        existingEmployee.setClient(client);

        Employee updatedEmployee = employeeRepository.save(existingEmployee);
        return convertToDto(updatedEmployee);
    }

    @Override
    public void deleteEmployee(int employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new EntityNotFoundException("Employee not found");
        }
        employeeRepository.deleteById(employeeId);
    }
}


