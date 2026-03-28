package com.college.management.dto.response;
import java.time.LocalDateTime;
public class AssignmentResponse {
    private Long id; private String title; private String description;
    private Long facultyId; private String facultyName; private Long subjectId; private String subjectName;
    private String fileUrl; private LocalDateTime deadline; private int totalSubmissions; private LocalDateTime createdAt;
    public AssignmentResponse() {}
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; } public void setTitle(String t) { this.title = t; }
    public String getDescription() { return description; } public void setDescription(String d) { this.description = d; }
    public Long getFacultyId() { return facultyId; } public void setFacultyId(Long f) { this.facultyId = f; }
    public String getFacultyName() { return facultyName; } public void setFacultyName(String f) { this.facultyName = f; }
    public Long getSubjectId() { return subjectId; } public void setSubjectId(Long s) { this.subjectId = s; }
    public String getSubjectName() { return subjectName; } public void setSubjectName(String s) { this.subjectName = s; }
    public String getFileUrl() { return fileUrl; } public void setFileUrl(String f) { this.fileUrl = f; }
    public LocalDateTime getDeadline() { return deadline; } public void setDeadline(LocalDateTime d) { this.deadline = d; }
    public int getTotalSubmissions() { return totalSubmissions; } public void setTotalSubmissions(int t) { this.totalSubmissions = t; }
    public LocalDateTime getCreatedAt() { return createdAt; } public void setCreatedAt(LocalDateTime c) { this.createdAt = c; }
}
