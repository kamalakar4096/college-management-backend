package com.college.management.dto.response;
import com.college.management.entity.Attendance.AttendanceStatus;
import java.time.LocalDate; import java.time.LocalDateTime;
public class AttendanceResponse {
    private Long id; private Long studentId; private String studentName;
    private Long subjectId; private String subjectName; private LocalDate date;
    private AttendanceStatus status; private LocalDateTime createdAt;
    public AttendanceResponse() {}
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public Long getStudentId() { return studentId; } public void setStudentId(Long s) { this.studentId = s; }
    public String getStudentName() { return studentName; } public void setStudentName(String s) { this.studentName = s; }
    public Long getSubjectId() { return subjectId; } public void setSubjectId(Long s) { this.subjectId = s; }
    public String getSubjectName() { return subjectName; } public void setSubjectName(String s) { this.subjectName = s; }
    public LocalDate getDate() { return date; } public void setDate(LocalDate d) { this.date = d; }
    public AttendanceStatus getStatus() { return status; } public void setStatus(AttendanceStatus s) { this.status = s; }
    public LocalDateTime getCreatedAt() { return createdAt; } public void setCreatedAt(LocalDateTime c) { this.createdAt = c; }
}
