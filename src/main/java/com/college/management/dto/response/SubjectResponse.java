package com.college.management.dto.response;
import java.time.LocalDateTime;
public class SubjectResponse {
    private Long id; private String subjectName; private Long facultyId; private String facultyName;
    private Integer semester; private Long departmentId; private String departmentName; private LocalDateTime createdAt;
    public SubjectResponse() {}
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getSubjectName() { return subjectName; } public void setSubjectName(String s) { this.subjectName = s; }
    public Long getFacultyId() { return facultyId; } public void setFacultyId(Long f) { this.facultyId = f; }
    public String getFacultyName() { return facultyName; } public void setFacultyName(String f) { this.facultyName = f; }
    public Integer getSemester() { return semester; } public void setSemester(Integer s) { this.semester = s; }
    public Long getDepartmentId() { return departmentId; } public void setDepartmentId(Long d) { this.departmentId = d; }
    public String getDepartmentName() { return departmentName; } public void setDepartmentName(String d) { this.departmentName = d; }
    public LocalDateTime getCreatedAt() { return createdAt; } public void setCreatedAt(LocalDateTime c) { this.createdAt = c; }
}
