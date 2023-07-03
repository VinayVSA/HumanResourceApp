package com.example.hra.Repository;

import com.example.hra.Entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job,String> {



    //List<Job> findByEmployeeIsNull();

    Job findByJobId(String jobId);

    void deleteByJobId(String jobId);

    //List<Job> findByDepartmentIdAndEmployeeIsNull(BigDecimal departmentId);
}
