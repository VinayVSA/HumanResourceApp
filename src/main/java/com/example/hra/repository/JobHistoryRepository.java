package com.example.hra.repository;

import com.example.hra.entity.JobHistory;
import com.example.hra.entity.JobHistoryId;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JobHistoryRepository extends JpaRepository<JobHistory,JobHistoryId> {

}
