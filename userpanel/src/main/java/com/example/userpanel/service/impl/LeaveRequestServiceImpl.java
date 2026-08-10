package com.example.userpanel.service.impl;


import com.example.userpanel.dto.LeaveRequestDto;
import com.example.userpanel.entity.Employee;
import com.example.userpanel.entity.LeaveRequest;
import com.example.userpanel.exception.ResourceNotFoundException;
import com.example.userpanel.repository.EmployeeRepository;
import com.example.userpanel.repository.LeaveRequestRepository;
import com.example.userpanel.service.LeaveRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private final LeaveRequestRepository leaveRequestRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public LeaveRequestDto applyForLeave(Long employeeId, LeaveRequestDto dto) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        // Rule: End date cannot be before start date
        if (dto.getEndDate().isBefore(dto.getStartDate())) {
            throw new IllegalArgumentException("End date cannot be before start date.");
        }

        LeaveRequest leaveRequest = LeaveRequest.builder()
                .employee(employee)
                .leaveType(dto.getLeaveType())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .reason(dto.getReason())
                .status("PENDING") // Automatically set to pending when applied
                .build();

        LeaveRequest savedLeave = leaveRequestRepository.save(leaveRequest);
        return mapToDto(savedLeave);
    }

    @Override
    public List<LeaveRequestDto> getLeaveHistory(Long employeeId) {
        return leaveRequestRepository.findByEmployeeId(employeeId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void cancelLeave(Long employeeId, Long leaveId) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveId)
                .orElseThrow(() -> new ResourceNotFoundException("Leave request not found"));

        // Rule: Ensure the leave belongs to the employee trying to cancel it
        if (!leaveRequest.getEmployee().getId().equals(employeeId)) {
            throw new IllegalStateException("You are not authorized to cancel this leave.");
        }

        // Rule: Can only cancel leaves that are still pending
        if (!leaveRequest.getStatus().equals("PENDING")) {
            throw new IllegalStateException("You can only cancel PENDING leave requests.");
        }

        leaveRequest.setStatus("CANCELLED");
        leaveRequestRepository.save(leaveRequest);
    }

    // Helper method to convert Entity to DTO
    private LeaveRequestDto mapToDto(LeaveRequest leave) {
        return LeaveRequestDto.builder()
                .id(leave.getId())
                .leaveType(leave.getLeaveType())
                .startDate(leave.getStartDate())
                .endDate(leave.getEndDate())
                .reason(leave.getReason())
                .status(leave.getStatus())
                .build();
    }
}