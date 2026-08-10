package com.example.userpanel.repository;

import com.example.userpanel.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    // Fetch all attendance records for a specific employee
    List<Attendance> findByEmployeeId(Long employeeId);

    // Find attendance for a specific employee on a specific date (useful for check-in/check-out logic)
    Optional<Attendance> findByEmployeeIdAndDate(Long employeeId, LocalDate date);
}