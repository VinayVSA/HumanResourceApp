package com.example.hra.services;
import com.example.hra.entities.Job;

import java.math.BigDecimal;
import java.util.List;
public interface JobService {
    String addJob(Job job);
    Job getJobById(String jobId);
    String updateJob(Job job);
    List<Job> getAllJobs();
    Job updateJobSalary(String jobId, BigDecimal minSalary, BigDecimal maxSalary);
    void delJobById(String jobId);
}
