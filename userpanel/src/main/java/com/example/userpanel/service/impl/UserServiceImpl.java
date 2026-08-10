package com.example.userpanel.service.impl;


import com.example.userpanel.dto.UserProfileDto;
import com.example.userpanel.entity.Employee;
import com.example.userpanel.exception.ResourceNotFoundException;
import com.example.userpanel.repository.EmployeeRepository;
import com.example.userpanel.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final EmployeeRepository employeeRepository;

    @Override
    public UserProfileDto getUserProfile(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        return mapToDto(employee);
    }

    @Override
    public UserProfileDto updateUserProfile(Long employeeId, UserProfileDto profileDto) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        // Only update allowed fields (e.g., employees shouldn't be able to change their own salary)
        employee.setPhone(profileDto.getPhone());
        employee.setFirstName(profileDto.getFirstName());
        employee.setLastName(profileDto.getLastName());

        Employee updatedEmployee = employeeRepository.save(employee);
        return mapToDto(updatedEmployee);
    }

    private UserProfileDto mapToDto(Employee employee) {
        return UserProfileDto.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .phone(employee.getPhone())
                .designation(employee.getDesignation())
                .joiningDate(employee.getJoiningDate())
                .build();
    }
}