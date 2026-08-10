package com.example.userpanel.service.impl;
import com.example.userpanel.dto.PayrollDto;
import com.example.userpanel.entity.Payroll;
import com.example.userpanel.exception.ResourceNotFoundException;
import com.example.userpanel.repository.PayrollRepository;
import com.example.userpanel.service.PayrollService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PayrollServiceImpl implements PayrollService {

    private final PayrollRepository payrollRepository;

    @Override
    public List<PayrollDto> getPayrollHistory(Long employeeId) {
        return payrollRepository.findByEmployeeId(employeeId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PayrollDto getMonthlyPayslip(Long employeeId, String month, Integer year) {
        Payroll payroll = payrollRepository.findByEmployeeIdAndMonthAndYear(employeeId, month, year)
                .orElseThrow(() -> new ResourceNotFoundException("Payslip not found for " + month + " " + year));
        return mapToDto(payroll);
    }

    private PayrollDto mapToDto(Payroll payroll) {
        return PayrollDto.builder()
                .id(payroll.getId())
                .month(payroll.getMonth())
                .year(payroll.getYear())
                .basicSalary(payroll.getBasicSalary())
                .allowances(payroll.getAllowances())
                .deductions(payroll.getDeductions())
                .netSalary(payroll.getNetSalary())
                .paymentDate(payroll.getPaymentDate())
                .status(payroll.getStatus())
                .build();
    }
}