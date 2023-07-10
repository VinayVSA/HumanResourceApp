package com.example.hra.repositories;

import com.example.hra.entities.JobHistory;
import com.example.hra.entities.JobHistoryId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface JobHistoryRepository extends JpaRepository<JobHistory,JobHistoryId> {
    List<JobHistory> findByIdEmployeeId(BigDecimal employeeId);

}
