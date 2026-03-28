package com.college.management.dto.request;
import jakarta.validation.constraints.NotBlank;
public class DepartmentRequest {
    @NotBlank private String departmentName;
    private Long hodId;
    public DepartmentRequest() {}
    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }
    public Long getHodId() { return hodId; }
    public void setHodId(Long hodId) { this.hodId = hodId; }
}
