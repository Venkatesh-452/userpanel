package com.example.userpanel.repository;



import com.example.userpanel.entity.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

    // Fetch leave history for a specific employee
    List<LeaveRequest> findByEmployeeId(Long employeeId);

    // Optionally, fetch leaves by status (e.g., find all "PENDING" leaves for an employee)
    List<LeaveRequest> findByEmployeeIdAndStatus(Long employeeId, String status);
}