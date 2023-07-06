package com.example.hra.service;

import com.example.hra.entity.*;
import com.example.hra.repository.DepartmentRepository;
import com.example.hra.repository.EmployeeRepository;
import com.example.hra.repository.JobHistoryRepository;
import com.example.hra.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

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
    public String createJobHistoryEntry(BigDecimal employeeId, Date startDate, String jobId, BigDecimal departmentId) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId);
        Job job = jobRepository.findByJobId(jobId);
        Department department = departmentRepository.findByDepartmentId(departmentId);

        if (employee == null || job == null || department == null) {
            System.out.println("Employee, Job, or Department not found.");
            return "Employee, Job, or Department not found.";
        }

        JobHistoryId jobHistoryId = new JobHistoryId(employeeId, startDate);
        if (jobHistoryRepository.existsById(jobHistoryId)) {
            System.out.println("Job history entry already exists for the given employee and start date.");
            return "Job history entry already exists for the given employee and start date.";
        }

        // Create the new job history entry
        Date endDate = new Date();
        JobHistory jobHistory = new JobHistory(jobHistoryId, employee, job, department, startDate, endDate);
        jobHistoryRepository.save(jobHistory);
        return "Record Created Successfully";
    }



}
