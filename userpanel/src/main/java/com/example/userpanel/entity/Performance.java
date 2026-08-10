package com.example.userpanel.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "performance_reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Performance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "review_period", nullable = false)
    private String reviewPeriod; // e.g., "Q1 2024", "Annual 2023"

    @Column(nullable = false)
    private Double rating; // e.g., 4.5 out of 5

    @Column(length = 1000)
    private String managerFeedback;

    @Column(length = 1000)
    private String goals;

    @Column(name = "review_date")
    private LocalDate reviewDate;
}