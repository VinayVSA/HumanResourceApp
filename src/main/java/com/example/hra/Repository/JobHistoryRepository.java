package com.example.hra.Repository;

import com.example.hra.Entity.JobHistory;
import com.example.hra.Entity.JobHistoryId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Optional;


public interface JobHistoryRepository extends JpaRepository<JobHistory,JobHistoryId> {

    //Integer calculateTotalDaysOfExperienceForAllEmployees();

    Optional<JobHistory> findByIdEmployeeIdAndEndDateIsNull(BigDecimal employeeId);

    //Integer calculateTotalDaysOfExperience(BigDecimal employeeId);
}
