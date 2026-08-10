package com.example.userpanel.controller;

import com.example.userpanel.dto.LeaveRequestDto;
import com.example.userpanel.service.LeaveRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/leave")
@RequiredArgsConstructor
public class UserLeaveController {

    private final LeaveRequestService leaveRequestService;

    @PostMapping("/apply")
    public ResponseEntity<LeaveRequestDto> applyForLeave(
            @RequestParam Long employeeId,
            @RequestBody @Valid LeaveRequestDto leaveRequestDto) {
        LeaveRequestDto appliedLeave = leaveRequestService.applyForLeave(employeeId, leaveRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(appliedLeave);
    }

    @GetMapping("/history")
    public ResponseEntity<List<LeaveRequestDto>> getLeaveHistory(@RequestParam Long employeeId) {
        List<LeaveRequestDto> history = leaveRequestService.getLeaveHistory(employeeId);
        return ResponseEntity.ok(history);
    }

    @DeleteMapping("/{leaveId}")
    public ResponseEntity<Void> cancelLeave(
            @RequestParam Long employeeId,
            @PathVariable Long leaveId) {
        leaveRequestService.cancelLeave(employeeId, leaveId);
        return ResponseEntity.noContent().build();
    }
}