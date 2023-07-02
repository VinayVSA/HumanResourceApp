package com.example.hra.Repository;

import com.example.hra.Entity.JobHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobHistoryRepository extends JpaRepository<JobHistory,Integer> {
}
