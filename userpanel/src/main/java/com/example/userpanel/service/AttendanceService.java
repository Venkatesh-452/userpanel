package com.example.userpanel.service;

import com.example.userpanel.dto.AttendanceDto;
import java.util.List;

public interface AttendanceService {
    AttendanceDto checkIn(Long employeeId);
    AttendanceDto checkOut(Long employeeId);
    List<AttendanceDto> getAttendanceHistory(Long employeeId);
}