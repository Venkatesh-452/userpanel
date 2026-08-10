package com.example.userpanel.service;

import com.example.userpanel.dto.PayrollDto;
import java.util.List;

public interface PayrollService {
    List<PayrollDto> getPayrollHistory(Long employeeId);
    PayrollDto getMonthlyPayslip(Long employeeId, String month, Integer year);
}