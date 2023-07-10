package com.example.hra.repositories;
import com.example.hra.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface JobRepository extends JpaRepository<Job,String> {
    Optional<Job> findByJobId(String jobId);
    void deleteByJobId(String jobId);

}
