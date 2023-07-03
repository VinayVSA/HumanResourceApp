package com.example.hra.service;
import com.example.hra.Entity.JobHistory;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public interface JobHistoryService {
    JobHistory createJobHistoryEntry(BigDecimal employeeId, Date startDate, String jobId, BigDecimal departmentId);

    JobHistory updateJobHistoryEndDate(BigDecimal employeeId, Date endDate);
    Map<String, Integer> findExperienceOfEmployee(BigDecimal employeeId);
    Map<String, Integer> findEmployeesWithLessThanOneYearExperience();
}
