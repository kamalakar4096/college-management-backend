package com.college.management.dto.response;
import com.college.management.entity.Role;
import java.time.LocalDateTime;
public class NotificationResponse {
    private Long id; private String title; private String message;
    private Long senderId; private String senderName; private Role targetRole;
    private Long targetDepartmentId; private String targetDepartmentName; private LocalDateTime createdAt;
    public NotificationResponse() {}
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; } public void setTitle(String t) { this.title = t; }
    public String getMessage() { return message; } public void setMessage(String m) { this.message = m; }
    public Long getSenderId() { return senderId; } public void setSenderId(Long s) { this.senderId = s; }
    public String getSenderName() { return senderName; } public void setSenderName(String s) { this.senderName = s; }
    public Role getTargetRole() { return targetRole; } public void setTargetRole(Role r) { this.targetRole = r; }
    public Long getTargetDepartmentId() { return targetDepartmentId; } public void setTargetDepartmentId(Long t) { this.targetDepartmentId = t; }
    public String getTargetDepartmentName() { return targetDepartmentName; } public void setTargetDepartmentName(String t) { this.targetDepartmentName = t; }
    public LocalDateTime getCreatedAt() { return createdAt; } public void setCreatedAt(LocalDateTime c) { this.createdAt = c; }
}
