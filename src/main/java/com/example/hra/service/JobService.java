package com.example.hra.service;

import com.example.hra.Entity.Job;

import java.math.BigDecimal;
import java.util.List;

public interface JobService {

    String addJob(Job job);

    String updateJob(Job job);

    List<Job> getAllJobs();

    Job getJobById(String jobId);

    Job updateJobSalary(String jobId, BigDecimal minSalary, BigDecimal maxSalary);

    void deleteJobById(String jobId);
}
