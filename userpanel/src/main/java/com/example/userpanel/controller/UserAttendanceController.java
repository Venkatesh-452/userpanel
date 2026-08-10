package com.example.userpanel.controller;


import com.example.userpanel.dto.AttendanceDto;
import com.example.userpanel.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/attendance")
@RequiredArgsConstructor
public class UserAttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/check-in")
    public ResponseEntity<AttendanceDto> checkIn(@RequestParam Long employeeId) {
        AttendanceDto attendance = attendanceService.checkIn(employeeId);
        return ResponseEntity.ok(attendance);
    }

    @PostMapping("/check-out")
    public ResponseEntity<AttendanceDto> checkOut(@RequestParam Long employeeId) {
        AttendanceDto attendance = attendanceService.checkOut(employeeId);
        return ResponseEntity.ok(attendance);
    }

    @GetMapping
    public ResponseEntity<List<AttendanceDto>> getAttendanceHistory(@RequestParam Long employeeId) {
        List<AttendanceDto> history = attendanceService.getAttendanceHistory(employeeId);
        return ResponseEntity.ok(history);
    }
}