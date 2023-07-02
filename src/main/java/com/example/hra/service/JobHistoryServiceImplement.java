package com.example.hra.service;

import com.example.hra.Entity.*;
import com.example.hra.Repository.DepartmentRepository;
import com.example.hra.Repository.EmployeeRepository;
import com.example.hra.Repository.JobHistoryRepository;
import com.example.hra.Repository.JobRepository;
import com.example.hra.service.JobHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class JobHistoryServiceImplement implements JobHistoryService {

    private JobHistoryRepository jobHistoryRepository;
    private JobRepository jobRepository;
    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;

    @Autowired
    public void setDepartmentRepository(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
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
    public void setJobHistoryRepository(JobHistoryRepository jobHistoryRepository) {
        this.jobHistoryRepository = jobHistoryRepository;
    }

    @Override
    public String addJobHistory(JobHistory jobHistory) {
        jobHistoryRepository.save(jobHistory);
        return "Record Created Successfully";
    }



    @Override
    public String updateJobHistoryEndDate(BigDecimal empId, Date endDate) {
        JobHistory jobHistory = (JobHistory) jobHistoryRepository.findById(new JobHistoryId(empId, new Date()))
                .orElse(null);
        if (jobHistory != null) {
            jobHistory.setEndDate(endDate);
            jobHistoryRepository.save(jobHistory);
            return "Record Modified Successfully";
        }
        return "Record Not Found";
    }

    @Override
    public String findExperienceOfEmployees(BigDecimal empId) {
        JobHistory jobHistory = jobHistoryRepository.findFirstByEmployee_EmployeeIdOrderByEndDateDesc(empId);
        if (jobHistory != null) {
            Date endDate = jobHistory.getEndDate() != null ? jobHistory.getEndDate() : new Date();
            long diff = endDate.getTime() - jobHistory.getStartDate().getTime();
            long years = diff / (1000L * 60L * 60L * 24L * 365L);
            diff = diff % (1000L * 60L * 60L * 24L * 365L);
            long months = diff / (1000L * 60L * 60L * 24L * 30L);
            diff = diff % (1000L * 60L * 60L * 24L * 30L);
            long days = diff / (1000L * 60L * 60L * 24L);

            return String.format("{\"years\": %d, \"months\": %d, \"days\": %d}", years, months, days);
        }
        return "{}";
    }



  /*  @Override
    public String listAllEmployeesWithLessThanOneYearExperience() {
        Calendar oneYearAgo = Calendar.getInstance();
        oneYearAgo.add(Calendar.YEAR, -1);
        Date startDate = oneYearAgo.getTime();
        long experienceInMillis = startDate.getTime();

        jobHistoryRepository.findAllByStartDateGreaterThanEqual(startDate).stream()
                .map(jobHistory -> {
                    long diff = jobHistory.getEndDate().getTime() - jobHistory.getStartDate().getTime();
                    return String.format("{\"years\": %d, \"months\": %d, \"days\": %d}",
                            diff / (1000L * 60L * 60L * 24L * 365L),
                            diff / (1000L * 60L * 60L * 24L * 30L),
                            diff / (1000L * 60L * 60L * 24L));
                }).collect(Collectors.toList());
    }*/


}
