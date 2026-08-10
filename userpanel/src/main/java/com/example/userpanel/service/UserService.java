package com.example.userpanel.service;


import com.example.userpanel.dto.UserProfileDto;

public interface UserService {
    UserProfileDto getUserProfile(Long employeeId);
    UserProfileDto updateUserProfile(Long employeeId, UserProfileDto profileDto);
}
