package com.techlabs.config;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

import com.techlabs.entity.Employee;


public class EmployeeFieldSetMapper implements FieldSetMapper<Employee> {
    @Override
    public Employee mapFieldSet(FieldSet fieldSet) {
        Employee employee = new Employee();
        employee.setEmployeeId(fieldSet.readInt("employeeId"));
        employee.setFirstname(fieldSet.readString("firstname"));
        employee.setLastname(fieldSet.readString("lastname"));
        employee.setEmail(fieldSet.readString("email"));
        employee.setAccountnumber(fieldSet.readLong("accountnumber"));
        employee.setSalary(fieldSet.readDouble("salary"));
        employee.setBalance(fieldSet.readDouble("balance"));
        return employee;
    }
}
