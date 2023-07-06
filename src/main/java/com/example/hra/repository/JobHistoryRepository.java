package com.example.hra.repository;

import com.example.hra.entity.JobHistory;
import com.example.hra.entity.JobHistoryId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface JobHistoryRepository extends JpaRepository<JobHistory,JobHistoryId> {
    List<JobHistory> findByIdEmployeeId(BigDecimal employeeId);

}
