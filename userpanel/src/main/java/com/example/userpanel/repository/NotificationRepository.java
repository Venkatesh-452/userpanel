package com.example.userpanel.repository;


import com.example.userpanel.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    // Fetch notifications for a specific employee, ordered by newest first
    List<Notification> findByEmployeeIdOrderByCreatedAtDesc(Long employeeId);

    // Fetch only unread notifications to show a badge on the dashboard UI
    List<Notification> findByEmployeeIdAndIsReadFalse(Long employeeId);
}