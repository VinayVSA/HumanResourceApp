package com.example.hra.controller;

import com.example.hra.Entity.JobHistory;
import com.example.hra.service.JobHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/job_history")
public class JobHistoryController {

    private JobHistoryService jobHistoryService;

    @Autowired
    public void setJobHistoryService(JobHistoryService jobHistoryService) {
        this.jobHistoryService = jobHistoryService;
    }

    @PostMapping("/{empid}/{startdate}/{job_id}/{department_id}")
    public ResponseEntity<String> addJobHistoryEntry(@PathVariable("empid") BigDecimal employeeId,
                                                     @PathVariable("startdate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                     @PathVariable("job_id") String jobId,
                                                     @PathVariable("department_id") BigDecimal departmentId) {
        JobHistory jobHistory = jobHistoryService.createJobHistoryEntry(employeeId, startDate, jobId, departmentId);
        if (jobHistory != null) {
            return ResponseEntity.ok("Record Created Successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation failed");
    }

    @PutMapping("/{empid}/{enddate}")
    public ResponseEntity<String> updateJobHistoryEndDate(@PathVariable("empid") BigDecimal employeeId,
                                                          @PathVariable("enddate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        JobHistory jobHistory = jobHistoryService.updateJobHistoryEndDate(employeeId, endDate);
        if (jobHistory != null) {
            return ResponseEntity.ok("Record Modified Successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation failed");
    }

    @GetMapping("/totalyearsofexperience/{emp_id}")
    public ResponseEntity<Map<String, Integer>> findExperienceOfEmployee(@PathVariable("emp_id") BigDecimal employeeId) {
        Map<String, Integer> experienceMap = jobHistoryService.findExperienceOfEmployee(employeeId);
        return ResponseEntity.ok(experienceMap);
    }

    @GetMapping("/lessthanoneyearexperience/{emp_id}")
    public ResponseEntity<Map<String, Integer>> findEmployeesWithLessThanOneYearExperience(@PathVariable("emp_id") BigDecimal employeeId) {
        Map<String, Integer> experienceMap = jobHistoryService.findEmployeesWithLessThanOneYearExperience();
        return ResponseEntity.ok(experienceMap);
    }
}
