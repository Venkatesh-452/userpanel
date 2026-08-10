package com.example.userpanel.repository;


import com.example.userpanel.entity.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PayrollRepository extends JpaRepository<Payroll, Long> {

    // Fetch all payslips for an employee
    List<Payroll> findByEmployeeId(Long employeeId);

    // Fetch a specific month's payslip for an employee
    Optional<Payroll> findByEmployeeIdAndMonthAndYear(Long employeeId, String month, Integer year);
}