package com.college.management.controller;
import com.college.management.dto.request.NotificationRequest;
import com.college.management.dto.response.ApiResponse;
import com.college.management.dto.response.NotificationResponse;
import com.college.management.entity.Role;
import com.college.management.security.UserPrincipal;
import com.college.management.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    private final NotificationService notificationService;
    public NotificationController(NotificationService notificationService) { this.notificationService = notificationService; }
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','DEAN','HOD','FACULTY')")
    public ResponseEntity<ApiResponse<NotificationResponse>> send(@Valid @RequestBody NotificationRequest request, @AuthenticationPrincipal UserPrincipal principal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Notification sent", notificationService.send(request, principal.getId())));
    }
    @GetMapping("/my-role")
    public ResponseEntity<ApiResponse<List<NotificationResponse>>> getForMyRole(@AuthenticationPrincipal UserPrincipal principal) {
        return ResponseEntity.ok(ApiResponse.success(notificationService.getForRole(Role.valueOf(principal.getRole()))));
    }
    @GetMapping("/sent")
    public ResponseEntity<ApiResponse<List<NotificationResponse>>> getSent(@AuthenticationPrincipal UserPrincipal principal) {
        return ResponseEntity.ok(ApiResponse.success(notificationService.getMine(principal.getId())));
    }
}
