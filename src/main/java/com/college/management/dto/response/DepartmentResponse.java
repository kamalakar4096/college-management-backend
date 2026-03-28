package com.college.management.dto.response;
import java.time.LocalDateTime;
public class DepartmentResponse {
    private Long id; private String departmentName; private Long hodId;
    private String hodName; private String hodEmail; private int totalMembers; private LocalDateTime createdAt;
    public DepartmentResponse() {}
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getDepartmentName() { return departmentName; } public void setDepartmentName(String n) { this.departmentName = n; }
    public Long getHodId() { return hodId; } public void setHodId(Long h) { this.hodId = h; }
    public String getHodName() { return hodName; } public void setHodName(String h) { this.hodName = h; }
    public String getHodEmail() { return hodEmail; } public void setHodEmail(String h) { this.hodEmail = h; }
    public int getTotalMembers() { return totalMembers; } public void setTotalMembers(int t) { this.totalMembers = t; }
    public LocalDateTime getCreatedAt() { return createdAt; } public void setCreatedAt(LocalDateTime c) { this.createdAt = c; }
}
