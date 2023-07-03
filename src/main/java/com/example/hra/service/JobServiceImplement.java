package com.example.hra.service;

import com.example.hra.Entity.Job;
import com.example.hra.Repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class JobServiceImplement implements JobService {

    private JobRepository jobRepository;

    @Autowired
    public void setJobRepository(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public String addJob(Job job) {
        jobRepository.save(job);
        return "Record Created Successfully";
    }

    @Override
    public String updateJob(Job job) {
        jobRepository.save(job);
        return "Record Modified Successfully";
    }

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public Job getJobById(String jobId) {
        return jobRepository.findByJobId(jobId);
    }

    @Override
    public Job updateJobSalary(String jobId, BigDecimal minSalary, BigDecimal maxSalary) {
        Job job = jobRepository.findByJobId(jobId);
        if (job != null) {
            job.setMinSalary(minSalary);
            job.setMaxSalary(maxSalary);
            jobRepository.save(job);
        }
        return job;
    }

    @Override
    public String deleteJobById(String jobId) {
        jobRepository.deleteByJobId(jobId);
        return "Record deleted Successfully";
    }
}

