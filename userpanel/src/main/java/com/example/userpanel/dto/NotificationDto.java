package com.example.userpanel.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class NotificationDto {
    private Long id;
    private String title;
    private String message;
    private LocalDateTime createdAt;
    private boolean isRead;
}