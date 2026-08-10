package com.example.userpanel.controller;
// Make sure this matches your package name!

import com.example.userpanel.entity.Employee;
import com.example.userpanel.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    // This perfectly matches the GET /api/employees/1 request from React!
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeProfile(@PathVariable Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        return ResponseEntity.ok(employee);
    }
}