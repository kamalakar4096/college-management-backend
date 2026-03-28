package com.college.management.service.impl;
import com.college.management.dto.request.NotificationRequest;
import com.college.management.dto.response.NotificationResponse;
import com.college.management.entity.*;
import com.college.management.exception.ResourceNotFoundException;
import com.college.management.repository.*;
import com.college.management.service.NotificationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    public NotificationServiceImpl(NotificationRepository notificationRepository, UserRepository userRepository, DepartmentRepository departmentRepository) {
        this.notificationRepository = notificationRepository; this.userRepository = userRepository; this.departmentRepository = departmentRepository;
    }
    @Override @Transactional
    public NotificationResponse send(NotificationRequest request, Long senderId) {
        User sender = userRepository.findById(senderId).orElseThrow(() -> new ResourceNotFoundException("User", senderId));
        Notification notification = new Notification();
        notification.setTitle(request.getTitle()); notification.setMessage(request.getMessage());
        notification.setSender(sender); notification.setTargetRole(request.getTargetRole());
        if (request.getTargetDepartmentId() != null) {
            Department dept = departmentRepository.findById(request.getTargetDepartmentId()).orElseThrow(() -> new ResourceNotFoundException("Department", request.getTargetDepartmentId()));
            notification.setTargetDepartment(dept);
        }
        return toResponse(notificationRepository.save(notification));
    }
    @Override public List<NotificationResponse> getForRole(Role role) {
        return notificationRepository.findNotificationsForRole(role).stream().map(this::toResponse).collect(Collectors.toList());
    }
    @Override public List<NotificationResponse> getMine(Long senderId) {
        return notificationRepository.findBySenderId(senderId).stream().map(this::toResponse).collect(Collectors.toList());
    }
    private NotificationResponse toResponse(Notification n) {
        NotificationResponse r = new NotificationResponse();
        r.setId(n.getId()); r.setTitle(n.getTitle()); r.setMessage(n.getMessage());
        r.setTargetRole(n.getTargetRole()); r.setCreatedAt(n.getCreatedAt());
        if (n.getSender() != null) { r.setSenderId(n.getSender().getId()); r.setSenderName(n.getSender().getName()); }
        if (n.getTargetDepartment() != null) { r.setTargetDepartmentId(n.getTargetDepartment().getId()); r.setTargetDepartmentName(n.getTargetDepartment().getDepartmentName()); }
        return r;
    }
}
