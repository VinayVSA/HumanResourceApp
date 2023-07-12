package com.example.hra.services;
import com.example.hra.entities.Job;
import com.example.hra.exceptions.JobNotFoundException;
import com.example.hra.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
@Service
public class JobServiceImplement implements JobService {
    private String jobNotFound = "Job Not Found";
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
        if(jobRepository.findByJobId(job.getJobId()).isPresent()) {
            jobRepository.save(job);
            return "Record Modified Successfully";
        }else{
        throw new JobNotFoundException(jobNotFound);}
    }
    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }
    @Override
    public Job getJobById(String jobId) {
        return jobRepository.findByJobId(jobId).orElseThrow(()->new JobNotFoundException(jobNotFound));}
    @Override
    public Job updateJobSalary(String jobId, BigDecimal minSalary, BigDecimal maxSalary) {
        Job job=jobRepository.findByJobId(jobId).orElseThrow(()->new JobNotFoundException(jobNotFound));
        job.setMinSalary(minSalary);
        job.setMaxSalary(maxSalary);
        jobRepository.save(job);
        return job;}
    @Override
    public void delJobById(String jobId)
    {
        if(jobRepository.findByJobId(jobId).isPresent()) {
            try {
                jobRepository.deleteByJobId(jobId);
            } catch (Exception re) {
                throw new JobNotFoundException("Cannot delete or update a parent row: a foreign key constraint fails");
            }
        }else{
            throw new JobNotFoundException(jobNotFound);
        }
    }
}

