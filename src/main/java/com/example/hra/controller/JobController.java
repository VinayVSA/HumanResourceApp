package com.example.hra.controller;

import com.example.hra.entity.Job;
import com.example.hra.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/job")
public class JobController {



   private JobService jobService;

   @Autowired
    public void setJobService(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping("")
    public ResponseEntity<String> addJob(@RequestBody Job job) {
        jobService.addJob(job);
        return ResponseEntity.status(HttpStatus.CREATED).body("Record Created Successfully");
    }



    @PutMapping("")
    public ResponseEntity<String> updateJob(@RequestBody Job job) {
        jobService.updateJob(job);
        return ResponseEntity.ok("Record Modified Successfully");
    }



    @GetMapping("")
    public ResponseEntity<List<Job>> getAllJobs() {
        List<Job> jobs = jobService.getAllJobs();
        return ResponseEntity.ok(jobs);
    }



    @PutMapping("/{jobId}/{minSalary}/{maxSalary}")
    public ResponseEntity<Job> updateSalaryRange(
            @PathVariable("jobId") String jobId,
            @PathVariable("minSalary") BigDecimal minSalary,
            @PathVariable("maxSalary") BigDecimal maxSalary
    ) {
        Job job = jobService.updateJobSalary(jobId,minSalary, maxSalary);
        if (job != null) {
            return ResponseEntity.ok(job);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{jobId}")
    public ResponseEntity<String> delJobById(@PathVariable("jobId") String jobId) {
        jobService.delJobById(jobId);
        return ResponseEntity.ok("Record deleted Successfully");
    }
}
