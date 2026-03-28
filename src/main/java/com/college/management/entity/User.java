package com.college.management.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) private String name;
    @Column(nullable = false, unique = true) private String email;
    @Column(nullable = false) private String password;
    private String phone;
    @Enumerated(EnumType.STRING) @Column(nullable = false) private Role role;
    @ManyToOne(fetch = FetchType.EAGER) @JoinColumn(name = "department_id") private Department department;
    @Column(nullable = false) private boolean active = true;
    private Integer semester;  // ✅ NEW
    private String branch;     // ✅ NEW
    @CreatedDate @Column(updatable = false) private LocalDateTime createdAt;
    @LastModifiedDate private LocalDateTime updatedAt;

    public User() {}

    public User(Long id, String name, String email, String password, String phone, Role role, Department department, boolean active, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id; this.name = name; this.email = email; this.password = password;
        this.phone = phone; this.role = role; this.department = department;
        this.active = active; this.createdAt = createdAt; this.updatedAt = updatedAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
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
    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    public Integer getSemester() { return semester; }           // ✅ NEW
    public void setSemester(Integer semester) { this.semester = semester; } // ✅ NEW
    public String getBranch() { return branch; }               // ✅ NEW
    public void setBranch(String branch) { this.branch = branch; }         // ✅ NEW
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
