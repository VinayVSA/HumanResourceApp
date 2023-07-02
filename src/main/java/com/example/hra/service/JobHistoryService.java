package com.example.hra.service;

import com.example.hra.Entity.JobHistory;
import com.example.hra.Entity.JobHistoryId;

import java.math.BigDecimal;
import java.util.Date;

public interface JobHistoryService {

    String addJobHistory(JobHistory jobHistory);

    String updateJobHistoryEndDate(BigDecimal empId, Date endDate);

    String findExperienceOfEmployees(BigDecimal empId);

    //String listAllEmployeesWithLessThanOneYearExperience();

}
