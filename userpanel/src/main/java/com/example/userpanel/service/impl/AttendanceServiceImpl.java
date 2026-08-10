package com.example.userpanel.service.impl;

import com.example.userpanel.dto.AttendanceDto;
import com.example.userpanel.entity.Attendance;
import com.example.userpanel.entity.Employee;
import com.example.userpanel.exception.ResourceNotFoundException;
import com.example.userpanel.repository.AttendanceRepository;
import com.example.userpanel.repository.EmployeeRepository;
import com.example.userpanel.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public AttendanceDto checkIn(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        LocalDate today = LocalDate.now();

        // Rule: Prevent double check-ins
        Optional<Attendance> existingAttendance = attendanceRepository.findByEmployeeIdAndDate(employeeId, today);
        if (existingAttendance.isPresent()) {
            throw new IllegalStateException("You have already checked in today.");
        }

        Attendance attendance = Attendance.builder()
                .employee(employee)
                .date(today)
                .checkInTime(LocalTime.now())
                .status("PRESENT") // Defaulting to present upon check-in
                .build();

        Attendance savedAttendance = attendanceRepository.save(attendance);
        return mapToDto(savedAttendance);
    }

    @Override
    public AttendanceDto checkOut(Long employeeId) {
        LocalDate today = LocalDate.now();

        // Rule: Must be checked in to check out
        Attendance attendance = attendanceRepository.findByEmployeeIdAndDate(employeeId, today)
                .orElseThrow(() -> new IllegalStateException("You have not checked in today."));

        // Rule: Prevent double check-outs
        if (attendance.getCheckOutTime() != null) {
            throw new IllegalStateException("You have already checked out today.");
        }

        attendance.setCheckOutTime(LocalTime.now());
        Attendance updatedAttendance = attendanceRepository.save(attendance);
        return mapToDto(updatedAttendance);
    }

    @Override
    public List<AttendanceDto> getAttendanceHistory(Long employeeId) {
        return attendanceRepository.findByEmployeeId(employeeId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // Helper method to convert Entity to DTO
    private AttendanceDto mapToDto(Attendance attendance) {
        return AttendanceDto.builder()
                .id(attendance.getId())
                .date(attendance.getDate())
                .checkInTime(attendance.getCheckInTime())
                .checkOutTime(attendance.getCheckOutTime())
                .status(attendance.getStatus())
                .build();
    }
}