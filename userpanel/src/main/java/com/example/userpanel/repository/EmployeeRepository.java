package com.example.userpanel.repository;

import com.example.userpanel.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Custom method to find an employee by their email (useful for login/security later)
    Optional<Employee> findByEmail(String email);

    // Custom method to check if an email is already registered
    boolean existsByEmail(String email);
}