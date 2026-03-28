package com.college.management.dto.response;
import java.time.LocalDateTime;
public class CourseResponse {
    private Long id; private String courseName; private String courseCode;
    private Integer credits; private Long departmentId; private String departmentName; private LocalDateTime createdAt;
    public CourseResponse() {}
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getCourseName() { return courseName; } public void setCourseName(String n) { this.courseName = n; }
    public String getCourseCode() { return courseCode; } public void setCourseCode(String c) { this.courseCode = c; }
    public Integer getCredits() { return credits; } public void setCredits(Integer c) { this.credits = c; }
    public Long getDepartmentId() { return departmentId; } public void setDepartmentId(Long d) { this.departmentId = d; }
    public String getDepartmentName() { return departmentName; } public void setDepartmentName(String d) { this.departmentName = d; }
    public LocalDateTime getCreatedAt() { return createdAt; } public void setCreatedAt(LocalDateTime c) { this.createdAt = c; }
}
