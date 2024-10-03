package com.techlabs.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.techlabs.entity.EmployeeTransaction;

@Configuration
public class EmployeeBatchConfig {
    // Reader
    @Bean
    public FlatFileItemReader<EmployeeTransaction> readEmployeeCsv() {
        FlatFileItemReader<EmployeeTransaction> employeeCsvReader = new FlatFileItemReader<>();
        employeeCsvReader.setResource(new FileSystemResource("employee_transactions.csv"));
        employeeCsvReader.setName("employeeCsvReader");
        employeeCsvReader.setLinesToSkip(1); // Skip the header line
        employeeCsvReader.setLineMapper(lineMapper());
        return employeeCsvReader;
    }

    private LineMapper<EmployeeTransaction> lineMapper() {
        DefaultLineMapper<EmployeeTransaction> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(","); // Set CSV delimiter
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("firstName", "lastName", "email", "accountNumber", "salary", "balance");

        BeanWrapperFieldSetMapper<EmployeeTransaction> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(EmployeeTransaction.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }

    // Processor
    @Bean
    public EmployeeProcessor employeeProcessor() {
        return new EmployeeProcessor(); 
    }

    // Writer
    @Bean
    public JdbcBatchItemWriter<EmployeeTransaction> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<EmployeeTransaction>()
                .sql("INSERT INTO emp_transactions (first_name, last_name, email, account_number, salary, balance) VALUES (:firstName, :lastName, :email, :accountNumber, :salary, :balance)")
                .dataSource(dataSource)
                .beanMapped()
                .build();
    }

    // Transaction Manager
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource); 
    }

    // Step
    @Bean
    public Step step1(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
                      FlatFileItemReader<EmployeeTransaction> reader, EmployeeProcessor processor, JdbcBatchItemWriter<EmployeeTransaction> writer) {
        return new StepBuilder("importcsvstep", jobRepository)
                .<EmployeeTransaction, EmployeeTransaction>chunk(2, transactionManager) 
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    // Job
    @Bean
    public Job importUserJob(JobRepository jobRepository, Step step1, JobCompletionNotificationListener listener) {
        return new JobBuilder("importUserJob", jobRepository)
                .listener(listener) 
                .start(step1) 
                .build();
    }
}

