package com.college.management.dto.request;

import com.college.management.entity.Role;
import jakarta.validation.constraints.*;

public class CreateUserRequest {
    @NotBlank @Size(min=2,max=100) private String name;
    @NotBlank @Email private String email;
    @NotBlank @Size(min=6) private String password;
    @Pattern(regexp="^[0-9]{10}$") private String phone;
    @NotNull private Role role;
    private Long departmentId;
    private Integer semester;  // ✅ NEW
    private String branch;     // ✅ NEW

    public CreateUserRequest() {}
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    public Long getDepartmentId() { return departmentId; }
    public void setDepartmentId(Long departmentId) { this.departmentId = departmentId; }
    public Integer getSemester() { return semester; }
    public void setSemester(Integer semester) { this.semester = semester; }
    public String getBranch() { return branch; }
    public void setBranch(String branch) { this.branch = branch; }
}