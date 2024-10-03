//package com.techlabs.controller;
//
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobParametersBuilder;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/app/admin")
//public class BatchTransactionController {
//
//    private static final Logger logger = LoggerFactory.getLogger(BatchTransactionController.class);
//
//    @Autowired
//    private JobLauncher jobLauncher;
//
//    @Autowired
//    private Job processEmployeeTransactionsJob;
//
//    @PostMapping("/process-transactions")
//    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
//    public ResponseEntity<String> processTransactions() {
//        try {
//            jobLauncher.run(processEmployeeTransactionsJob, new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters());
//            return ResponseEntity.ok("Employee transaction processing job started successfully.");
//        } catch (Exception e) {
//            logger.error("Error starting employee transaction processing job: {}", e.getMessage());
//            return ResponseEntity.status(500).body("Failed to start employee transaction processing job: " + e.getMessage());
//        }
//    }
//}
//
