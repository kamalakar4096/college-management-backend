package com.college.management.dto.response;

import com.college.management.entity.Role;
import java.time.LocalDateTime;

public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private Role role;
    private Long departmentId;
    private String departmentName;
    private boolean active;
    private LocalDateTime createdAt;
    private Integer semester;  // ✅ NEW
    private String branch;     // ✅ NEW

    public UserResponse() {}
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getName() { return name; } public void setName(String name) { this.name = name; }
    public String getEmail() { return email; } public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; } public void setPhone(String phone) { this.phone = phone; }
    public Role getRole() { return role; } public void setRole(Role role) { this.role = role; }
    public Long getDepartmentId() { return departmentId; } public void setDepartmentId(Long d) { this.departmentId = d; }
    public String getDepartmentName() { return departmentName; } public void setDepartmentName(String d) { this.departmentName = d; }
    public boolean isActive() { return active; } public void setActive(boolean active) { this.active = active; }
    public LocalDateTime getCreatedAt() { return createdAt; } public void setCreatedAt(LocalDateTime c) { this.createdAt = c; }
    public Integer getSemester() { return semester; } public void setSemester(Integer semester) { this.semester = semester; }
    public String getBranch() { return branch; } public void setBranch(String branch) { this.branch = branch; }
}