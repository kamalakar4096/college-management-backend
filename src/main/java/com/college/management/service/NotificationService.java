package com.college.management.service;
import com.college.management.dto.request.NotificationRequest;
import com.college.management.dto.response.NotificationResponse;
import com.college.management.entity.Role;
import java.util.List;
public interface NotificationService {
    NotificationResponse send(NotificationRequest request, Long senderId);
    List<NotificationResponse> getForRole(Role role);
    List<NotificationResponse> getMine(Long senderId);
}
