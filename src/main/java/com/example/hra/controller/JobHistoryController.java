package com.example.hra.controller;

import com.example.hra.service.JobHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/job_history")
public class JobHistoryController {

    private JobHistoryService jobHistoryService;

    @Autowired
    public void setJobHistoryService(JobHistoryService jobHistoryService) {
        this.jobHistoryService = jobHistoryService;
    }

    @PostMapping("/{employee_id}/{start_date}/{job_id}/{department_id}")
    public ResponseEntity<String> addJobHistoryEntry(@PathVariable("employee_id") BigDecimal employeeId,
                                                     @PathVariable("start_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                     @PathVariable("job_id") String jobId,
                                                     @PathVariable("department_id") BigDecimal departmentId) {
        String jobHistory = jobHistoryService.createJobHistoryEntry(employeeId, startDate, jobId, departmentId);
        if (jobHistory.equals("Record Created Successfully")) {
            return ResponseEntity.ok(jobHistory);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation failed");
    }

}
