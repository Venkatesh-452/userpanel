package com.example.userpanel.controller;
 // <-- CHANGE THIS IF NECESSARY

import com.example.userpanel.dto.PayrollDto; // <-- CHANGE THIS IF NECESSARY
import com.example.userpanel.service.PayrollService; // <-- CHANGE THIS IF NECESSARY
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/payroll")
@RequiredArgsConstructor
public class UserPayrollController {

    private final PayrollService payrollService;

    @GetMapping
    public ResponseEntity<List<PayrollDto>> getPayrollHistory(@RequestParam Long employeeId) {
        return ResponseEntity.ok(payrollService.getPayrollHistory(employeeId));
    }

    @GetMapping("/payslip")
    public ResponseEntity<PayrollDto> getPayslip(
            @RequestParam Long employeeId,
            @RequestParam String month,
            @RequestParam Integer year) {
        return ResponseEntity.ok(payrollService.getMonthlyPayslip(employeeId, month, year));
    }
}