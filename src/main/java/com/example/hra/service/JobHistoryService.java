package com.example.hra.service;

import java.math.BigDecimal;
import java.util.Date;

public interface JobHistoryService {
    String createJobHistoryEntry(BigDecimal employeeId, Date startDate, String jobId, BigDecimal departmentId);

}
