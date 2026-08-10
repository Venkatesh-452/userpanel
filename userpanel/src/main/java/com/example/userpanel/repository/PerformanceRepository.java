package com.example.userpanel.repository;

import com.example.userpanel.entity.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerformanceRepository extends JpaRepository<Performance, Long> {

    // Fetch performance reviews for a specific employee
    List<Performance> findByEmployeeId(Long employeeId);
}