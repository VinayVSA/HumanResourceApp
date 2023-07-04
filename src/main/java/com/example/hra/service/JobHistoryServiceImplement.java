package com.example.hra.service;

import com.example.hra.Entity.*;
import com.example.hra.Repository.DepartmentRepository;
import com.example.hra.Repository.EmployeeRepository;
import com.example.hra.Repository.JobHistoryRepository;
import com.example.hra.Repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@Service
public class JobHistoryServiceImplement implements JobHistoryService {

    private JobHistoryRepository jobHistoryRepository;
    private EmployeeRepository employeeRepository;
    private JobRepository jobRepository;
    private DepartmentRepository departmentRepository;

    @Autowired
    public void setJobHistoryRepository(JobHistoryRepository jobHistoryRepository) {
        this.jobHistoryRepository = jobHistoryRepository;
    }
    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @Autowired
    public void setJobRepository(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Autowired
    public void setDepartmentRepository(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public JobHistory createJobHistoryEntry(BigDecimal employeeId, Date startDate, String jobId, BigDecimal departmentId) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId);
        Job job = jobRepository.findByJobId(jobId);
        Department department = departmentRepository.findByDepartmentId(departmentId);

        if (employee != null && job != null && department != null) {
            JobHistoryId jobHistoryId = new JobHistoryId(employeeId, startDate);
            Date endDate= new Date();
            JobHistory jobHistory = new JobHistory(jobHistoryId, employee, job, department, startDate,endDate);
            return jobHistoryRepository.save(jobHistory);
        }
        return null;
    }

    @Override
    public JobHistory updateJobHistoryEndDate(BigDecimal employeeId, Date endDate) {
        Optional<JobHistory> jobHistoryOptional = jobHistoryRepository.findByIdEmployeeIdAndEndDateIsNull(employeeId);
        if (jobHistoryOptional.isPresent()) {
            JobHistory jobHistory = jobHistoryOptional.get();
            jobHistory.setEndDate(endDate);
            return jobHistoryRepository.save(jobHistory);
        }
        return null;
    }
/*
    @Override
    public Map<String, Integer> findExperienceOfEmployee(BigDecimal employeeId) {
        Map<String, Integer> experienceMap = new HashMap<>();

        Integer totalDays = jobHistoryRepository.calculateTotalDaysOfExperience(employeeId);
        int years = totalDays / 365;
        int months = (totalDays % 365) / 30;
        int days = (totalDays % 365) % 30;

        experienceMap.put("years", years);
        experienceMap.put("months", months);
        experienceMap.put("days", days);

        return experienceMap;
    }

    @Override
    public Map<String, Integer> findEmployeesWithLessThanOneYearExperience() {
        Map<String, Integer> experienceMap = new HashMap<>();

        Integer totalDays = jobHistoryRepository.calculateTotalDaysOfExperienceForAllEmployees();
        int years = totalDays / 365;
        int months = (totalDays % 365) / 30;
        int days = (totalDays % 365) % 30;

        experienceMap.put("years", years);
        experienceMap.put("months", months);
        experienceMap.put("days", days);

        return experienceMap;
    }*/
}
