package com.example.hra.controller;
import com.example.hra.exception.ValidationFailedException;
import com.example.hra.service.JobHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
                                                     @PathVariable String job_id,@PathVariable("department_id") BigDecimal departmentId) {
        String jobHistory = jobHistoryService.createJobHistoryEntry(employeeId,job_id, startDate, departmentId);
        if (jobHistory.equals("Record Created Successfully"))
            return ResponseEntity.ok(jobHistory);
        else
            throw new ValidationFailedException("Validation Failed");
    }
    @PutMapping("/{employee_id}/{end_date}")
    public ResponseEntity<String> modifyJobHistory(
            @PathVariable("employee_id") BigDecimal employeeId,
            @PathVariable("end_date")@DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        String result = jobHistoryService.modifyJobHistory(employeeId, endDate);
        if (result.equals("Record Modified Successfully"))
            return ResponseEntity.ok(result);
        else
            throw new ValidationFailedException("Validation Failed"+" "+result);
    }
    @GetMapping("/totalyearsofexperience/{emp_id}")
    public ResponseEntity<Map<String, Integer>> findExperienceOfEmployee(
            @PathVariable("emp_id") BigDecimal employeeId) {
        Map<String, Integer> experienceMap = jobHistoryService.findExperienceOfEmployee(employeeId);
        return ResponseEntity.ok(experienceMap);
    }
    @GetMapping("/lessthanoneyearexperience/{emp_id}")
    public ResponseEntity<Map<String, Long>> getEmployeeExperienceLessThanOneYear(
            @PathVariable("emp_id") BigDecimal employeeId) {
        Duration duration = jobHistoryService.getEmployeeExperienceLessThanOneYear(employeeId);
        long years = duration.toDays() / 365;
        long months = (duration.toDays() % 365) / 30;
        long days = duration.toDays() % 30;
        Map<String, Long> result = new HashMap<>();
        result.put("years", years);
        result.put("months", months);
        result.put("days", days);
        return ResponseEntity.ok(result);
    }
}