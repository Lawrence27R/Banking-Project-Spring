package com.techlabs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/app/banks")
public class BatchController {

    private static final Logger logger = LoggerFactory.getLogger(BatchController.class);

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job exportEmployeeJob;
    
    @Autowired
    private Job importUserJob;


    @Autowired
    private Job exportBeneficiaryJob;

    @PostMapping("/export-employees")
    public ResponseEntity<String> exportEmployees() {
        try {
            jobLauncher.run(exportEmployeeJob, new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters());
            return ResponseEntity.ok("Employee export job started successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to start employee export job: " + e.getMessage());
        }
    }

    @PostMapping("/export-beneficiaries")
    public ResponseEntity<String> exportBeneficiaries() {
        try {
            jobLauncher.run(exportBeneficiaryJob, new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters());
            return ResponseEntity.ok("Beneficiary export job started successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to start beneficiary export job: " + e.getMessage());
        }
    }
    
    @PostMapping("/process")
    public ResponseEntity<String> process() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis())
                .toJobParameters();
            jobLauncher.run(importUserJob, jobParameters);
            return ResponseEntity.ok("Batch job has been started.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to start job: " + e.getMessage());
        }
    }
}

