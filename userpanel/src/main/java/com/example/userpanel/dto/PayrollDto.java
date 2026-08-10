package com.example.userpanel.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class PayrollDto {
    private Long id;
    private String month;
    private Integer year;
    private Double basicSalary;
    private Double allowances;
    private Double deductions;
    private Double netSalary;
    private LocalDate paymentDate;
    private String status;
}