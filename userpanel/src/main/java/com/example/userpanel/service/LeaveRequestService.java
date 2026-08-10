package com.example.userpanel.service;

import com.example.userpanel.dto.LeaveRequestDto;
import java.util.List;

public interface LeaveRequestService {
    LeaveRequestDto applyForLeave(Long employeeId, LeaveRequestDto dto);
    List<LeaveRequestDto> getLeaveHistory(Long employeeId);
    void cancelLeave(Long employeeId, Long leaveId);
}