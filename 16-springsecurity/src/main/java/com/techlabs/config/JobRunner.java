//package com.techlabs.config;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class JobRunner {
//
//    @Autowired
//    private JobLauncher jobLauncher;
//
//    @Autowired
//    private Job exportEmployeeJob;
//
//
//    @Autowired
//    private Job exportBeneficiaryJob;
//
//    public void runEmployeeJob() {
//        try {
//            jobLauncher.run(exportEmployeeJob, new JobParameters());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public void runBeneficiaryJob() {
//        try {
//            jobLauncher.run(exportBeneficiaryJob, new JobParameters());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
