package com.example.hra.services;
import com.example.hra.entities.*;
import com.example.hra.exceptions.DepartmentNotFoundException;
import com.example.hra.exceptions.EmployeeNotFoundException;
import com.example.hra.exceptions.JobNotFoundException;
import com.example.hra.repositories.DepartmentRepository;
import com.example.hra.repositories.EmployeeRepository;
import com.example.hra.repositories.JobHistoryRepository;
import com.example.hra.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;
@Service
public class JobHistoryServiceImplement implements JobHistoryService {
    private JobHistoryRepository jobHistoryRepository;
    private EmployeeRepository employeeRepository;
    private JobRepository jobRepository;
    private DepartmentRepository departmentRepository;
    @Autowired
    public void setJobHistoryRepository(JobHistoryRepository jobHistoryRepository) {
        this.jobHistoryRepository = jobHistoryRepository;
    }
    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @Autowired
    public void setJobRepository(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
    @Autowired
    public void setDepartmentRepository(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    @Transactional
    public String createJobHistoryEntry(BigDecimal employeeId,String jobId, Date startDate, BigDecimal departmentId) {
        Employee employee=employeeRepository.findByEmployeeId(employeeId).orElseThrow(()->new EmployeeNotFoundException("Employee Not Found"));
        Department department=departmentRepository.findByDepartmentId(departmentId).orElseThrow(()->new DepartmentNotFoundException("Department Not Found"));
        Job job = jobRepository.findByJobId(jobId).orElseThrow(()->new JobNotFoundException("Job Not Found"));
        JobHistoryId jobHistoryId = new JobHistoryId(employeeId,startDate,jobId,departmentId);
        if (jobHistoryRepository.existsById(jobHistoryId))
            return "Job history entry already exists for the given employee and start date.";
       Date endDate = new Date();
       JobHistory jobHistory = new JobHistory();
       jobHistory.setDepartment(department);
       jobHistory.setEmployee(employee);
       jobHistory.setJob(job);
       jobHistory.setId(jobHistoryId);
       jobHistory.setEndDate(endDate);
       jobHistoryRepository.save(jobHistory);
       return "Record Created Successfully";
    }
    @Override
    public String modifyJobHistory(BigDecimal employeeId, Date endDate) {
        String s = new String();
        Employee employee = employeeRepository.findByEmployeeId(employeeId).orElseThrow(()->new EmployeeNotFoundException("Employee Not Found"));
        List<JobHistory> jobHistory = jobHistoryRepository.findAll();
        for (JobHistory j:jobHistory) {
            Employee employee1 = j.getEmployee();
            int comparisonResult = employee.getEmployeeId().compareTo(employee1.getEmployeeId());
            if (comparisonResult == 0) {
                j.setEndDate(endDate);
                System.out.println(endDate);
                s="Record Modified Successfully";
                jobHistoryRepository.save(j);
                break;
            }
            else
                s = "Not Updated";
        }
        return s;
    }
    @Override
    public Map<String, Integer> findExperienceOfEmployee(BigDecimal employeeId) {
        employeeRepository.findByEmployeeId(employeeId).orElseThrow(()->new EmployeeNotFoundException("Employee Not Found"));
        List<JobHistory> jobHistoryList = jobHistoryRepository.findByIdEmployeeId(employeeId);
        int totalYears = 0;
        int totalMonths = 0;
        int totalDays = 0;
        for (JobHistory jobHistory : jobHistoryList) {
            Date startDate = jobHistory.getStartDate();
            Date endDate = jobHistory.getEndDate();
            if (startDate != null && endDate != null) {
                LocalDate startLocalDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate endLocalDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                Period period = Period.between(startLocalDate, endLocalDate);
                totalYears += period.getYears();
                totalMonths += period.getMonths();
                totalDays += period.getDays();
            }
        }
        totalYears += totalMonths / 12;
        totalMonths = totalMonths % 12;
        Map<String, Integer> experienceMap = new HashMap<>();
        experienceMap.put("years", totalYears);
        experienceMap.put("months", totalMonths);
        experienceMap.put("days", totalDays);
        return experienceMap;
    }
    @Override
    public Duration getEmployeeExperienceLessThanOneYear(BigDecimal employeeId) {
        List<JobHistory> jobHistoryList = jobHistoryRepository.findByIdEmployeeId(employeeId);
        Date currentDate = new Date();
        Duration totalDuration = Duration.ZERO;
        for (JobHistory jobHistory : jobHistoryList) {
            Date startDate = jobHistory.getStartDate();
            Date endDate = jobHistory.getEndDate() != null ? jobHistory.getEndDate() : currentDate;
            long durationInMillis = endDate.getTime() - startDate.getTime();
            totalDuration = totalDuration.plusMillis(durationInMillis);
        }
        return totalDuration;
    }
}
