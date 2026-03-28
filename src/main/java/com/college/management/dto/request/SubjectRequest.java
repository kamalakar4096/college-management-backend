package com.college.management.dto.request;
import jakarta.validation.constraints.*;
public class SubjectRequest {
    @NotBlank private String subjectName;
    private Long facultyId;
    @NotNull @Min(1) @Max(8) private Integer semester;
    @NotNull private Long departmentId;
    public SubjectRequest() {}
    public String getSubjectName() { return subjectName; }
    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }
    public Long getFacultyId() { return facultyId; }
    public void setFacultyId(Long facultyId) { this.facultyId = facultyId; }
    public Integer getSemester() { return semester; }
    public void setSemester(Integer semester) { this.semester = semester; }
    public Long getDepartmentId() { return departmentId; }
    public void setDepartmentId(Long departmentId) { this.departmentId = departmentId; }
}
