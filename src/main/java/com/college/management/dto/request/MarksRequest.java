package com.college.management.dto.request;
import com.college.management.entity.Marks.ExamType;
import jakarta.validation.constraints.*;
public class MarksRequest {
    @NotNull private Long studentId;
    @NotNull private Long subjectId;
    @NotNull @DecimalMin("0.0") @DecimalMax("100.0") private Double marks;
    @NotNull private ExamType examType;
    public MarksRequest() {}
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    public Long getSubjectId() { return subjectId; }
    public void setSubjectId(Long subjectId) { this.subjectId = subjectId; }
    public Double getMarks() { return marks; }
    public void setMarks(Double marks) { this.marks = marks; }
    public ExamType getExamType() { return examType; }
    public void setExamType(ExamType examType) { this.examType = examType; }
}
