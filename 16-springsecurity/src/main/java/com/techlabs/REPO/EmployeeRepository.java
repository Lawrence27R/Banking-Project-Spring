package com.techlabs.REPO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
