package com.college.management.dto.request;
import com.college.management.entity.Role;
import jakarta.validation.constraints.NotBlank;
public class NotificationRequest {
    @NotBlank private String title;
    @NotBlank private String message;
    private Role targetRole;
    private Long targetDepartmentId;
    public NotificationRequest() {}
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public Role getTargetRole() { return targetRole; }
    public void setTargetRole(Role targetRole) { this.targetRole = targetRole; }
    public Long getTargetDepartmentId() { return targetDepartmentId; }
    public void setTargetDepartmentId(Long targetDepartmentId) { this.targetDepartmentId = targetDepartmentId; }
}
