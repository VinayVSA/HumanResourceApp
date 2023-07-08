package com.example.hra.service;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Date;
import java.util.Map;

public interface JobHistoryService {
    String createJobHistoryEntry(BigDecimal employeeId,String jobId, Date startDate, BigDecimal departmentId);

    String modifyJobHistory(BigDecimal employeeId, Date endDate);

    Map<String, Integer> findExperienceOfEmployee(BigDecimal employeeId);

    Duration getEmployeeExperienceLessThanOneYear(BigDecimal employeeId);
}
