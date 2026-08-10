package com.example.userpanel.controller;


import com.example.userpanel.dto.UserProfileDto;
import com.example.userpanel.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/profile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserProfileDto> getProfile(@RequestParam Long employeeId) {
        return ResponseEntity.ok(userService.getUserProfile(employeeId));
    }

    @PutMapping
    public ResponseEntity<UserProfileDto> updateProfile(
            @RequestParam Long employeeId,
            @RequestBody UserProfileDto profileDto) {
        return ResponseEntity.ok(userService.updateUserProfile(employeeId, profileDto));
    }
}