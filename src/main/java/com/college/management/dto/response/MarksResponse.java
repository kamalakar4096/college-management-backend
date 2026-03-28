package com.college.management.dto.response;
import com.college.management.entity.Marks.ExamType;
import java.time.LocalDateTime;
public class MarksResponse {
    private Long id; private Long studentId; private String studentName;
    private Long subjectId; private String subjectName; private Double marks;
    private ExamType examType; private LocalDateTime createdAt;
    public MarksResponse() {}
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public Long getStudentId() { return studentId; } public void setStudentId(Long s) { this.studentId = s; }
    public String getStudentName() { return studentName; } public void setStudentName(String s) { this.studentName = s; }
    public Long getSubjectId() { return subjectId; } public void setSubjectId(Long s) { this.subjectId = s; }
    public String getSubjectName() { return subjectName; } public void setSubjectName(String s) { this.subjectName = s; }
    public Double getMarks() { return marks; } public void setMarks(Double m) { this.marks = m; }
    public ExamType getExamType() { return examType; } public void setExamType(ExamType e) { this.examType = e; }
    public LocalDateTime getCreatedAt() { return createdAt; } public void setCreatedAt(LocalDateTime c) { this.createdAt = c; }
}
