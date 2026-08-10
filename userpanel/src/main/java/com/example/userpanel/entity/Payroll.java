package com.example.userpanel.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "payroll")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(nullable = false)
    private String month; // e.g., "JANUARY"

    @Column(nullable = false)
    private Integer year; // e.g., 2024

    @Column(name = "basic_salary", nullable = false)
    private Double basicSalary;

    private Double allowances; // Bonus, HRA, etc.

    private Double deductions; // Taxes, Provident Fund, etc.

    @Column(name = "net_salary", nullable = false)
    private Double netSalary;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    // Status can be "PENDING", "PAID"
    @Column(nullable = false)
    private String status;
}