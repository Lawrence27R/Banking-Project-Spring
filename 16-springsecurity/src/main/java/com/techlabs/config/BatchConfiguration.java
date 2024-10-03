package com.techlabs.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.techlabs.entity.Beneficiary;
import com.techlabs.entity.Employee;

@Configuration
public class BatchConfiguration {

    // Employee Batch Configuration

	@Bean
	public JdbcCursorItemReader<Employee> employeeReader(DataSource dataSource) {
	    return new JdbcCursorItemReaderBuilder<Employee>()
	            .dataSource(dataSource)
	            .name("employeeReader")
	            .sql("SELECT employee_id, firstname, lastname, email, salary, accountnumber, balance FROM employees") // Include balance in SQL query
	            .rowMapper((rs, rowNum) -> {
	                Employee employee = new Employee();
	                employee.setEmployeeId(rs.getInt("employee_id"));
	                employee.setFirstname(rs.getString("firstname"));
	                employee.setLastname(rs.getString("lastname"));
	                employee.setEmail(rs.getString("email"));
	                employee.setSalary(rs.getDouble("salary"));
	                employee.setAccountnumber(rs.getLong("accountnumber"));
	                employee.setBalance(rs.getDouble("balance"));  // Set balance
	                return employee;
	            })
	            .build();
	}

	@Bean
	public FlatFileItemWriter<Employee> employeeWriter() {
	    return new FlatFileItemWriterBuilder<Employee>()
	            .name("employeeCsvWriter")
	            .resource(new FileSystemResource("C:/Users/lawrence.rodriques/OneDrive - Aurionpro Solutions Limited/Desktop/Output/employees.csv"))
	            .delimited()
	            .delimiter(",")
	            .names("firstname", "lastname", "email", "accountnumber", "salary", "balance")  // Add balance field
	            .headerCallback(writer -> writer.write("firstname,lastname,email,accountnumber,salary,balance"))  // Add balance to header
	            .build();
	}


    @Bean
    public Step employeeStep(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
                             JdbcCursorItemReader<Employee> reader, FlatFileItemWriter<Employee> writer) {
        return new StepBuilder("employeeStep", jobRepository)
                .<Employee, Employee>chunk(10, transactionManager)
                .reader(reader)
                .writer(writer)
                .build();
    }

    @Bean
    @Qualifier("exportEmployeeJob")
    public Job exportEmployeeJob(JobRepository jobRepository, Step employeeStep) {
        return new JobBuilder("exportEmployeeJob", jobRepository)
                .start(employeeStep)
                .build();
    }

    // Beneficiary Batch Configuration

    @Bean
    public JdbcCursorItemReader<Beneficiary> beneficiaryReader(DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<Beneficiary>()
                .dataSource(dataSource)
                .name("beneficiaryReader")
                .sql("SELECT beneficiary_name, beneficiary_account_number, beneficiary_ifsc, balance FROM beneficiaries")  // Updated query
                .rowMapper((rs, rowNum) -> {
                    Beneficiary beneficiary = new Beneficiary();
                    beneficiary.setBeneficiaryName(rs.getString("beneficiary_name"));
                    beneficiary.setBeneficiaryAccountNumber(rs.getLong("beneficiary_account_number"));
                    beneficiary.setBeneficiaryIfsc(rs.getString("beneficiary_ifsc"));
                    beneficiary.setBalance(rs.getDouble("balance"));  // Updated to balance
                    return beneficiary;
                })
                .build();
    }

    @Bean
    public FlatFileItemWriter<Beneficiary> beneficiaryWriter() {
        return new FlatFileItemWriterBuilder<Beneficiary>()
                .name("beneficiaryCsvWriter")
                .resource(new FileSystemResource("C:/Users/lawrence.rodriques/OneDrive - Aurionpro Solutions Limited/Desktop/Output/beneficiaries.csv"))
                .delimited()
                .delimiter(",")
                .names("beneficiaryName", "beneficiaryAccountNumber", "beneficiaryIfsc", "balance")  // Updated to balance
                .headerCallback(writer -> writer.write("beneficiaryName,beneficiaryAccountNumber,beneficiaryIfsc,balance"))  // Update header
                .build();
    }


    @Bean
    public Step beneficiaryStep(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
                                JdbcCursorItemReader<Beneficiary> reader, FlatFileItemWriter<Beneficiary> writer) {
        return new StepBuilder("beneficiaryStep", jobRepository)
                .<Beneficiary, Beneficiary>chunk(10, transactionManager)
                .reader(reader)
                .writer(writer)
                .build();
    }

    @Bean
    @Qualifier("exportBeneficiaryJob")
    public Job exportBeneficiaryJob(JobRepository jobRepository, Step beneficiaryStep) {
        return new JobBuilder("exportBeneficiaryJob", jobRepository)
                .start(beneficiaryStep)
                .build();
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}


