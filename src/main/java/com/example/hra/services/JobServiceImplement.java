package com.example.hra.services;
import com.example.hra.entities.Job;
import com.example.hra.exceptions.JobNotFoundException;
import com.example.hra.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return "Record Created Successfully";}
    @Override
    public String updateJob(Job job) {
        jobRepository.findByJobId(job.getJobId()).orElseThrow(()-> new JobNotFoundException("Job Not Found"));
        jobRepository.save(job);
        return "Record Modified Successfully";}
    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }
    @Override
    public Job getJobById(String jobId) {
        return jobRepository.findByJobId(jobId).orElseThrow(()->new JobNotFoundException("Job Not Found"));}
    @Override
    public Job updateJobSalary(String jobId, BigDecimal minSalary, BigDecimal maxSalary) {
        Job job=jobRepository.findByJobId(jobId).orElseThrow(()->new JobNotFoundException("Job Not Found"));
        job.setMinSalary(minSalary);
        job.setMaxSalary(maxSalary);
        jobRepository.save(job);
        return job;}
    @Override
    public void delJobById(String jobId)
    {
        jobRepository.findByJobId(jobId).orElseThrow(()->new JobNotFoundException("Job Not Found"));
        try{
            jobRepository.deleteByJobId(jobId);}
        catch (Exception re){
            throw new JobNotFoundException("Cannot delete or update a parent row: a foreign key constraint fails");}
        }
}

