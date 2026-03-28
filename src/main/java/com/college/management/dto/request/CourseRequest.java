package com.college.management.dto.request;
import jakarta.validation.constraints.*;
public class CourseRequest {
    @NotBlank private String courseName;
    @NotBlank private String courseCode;
    @Min(1) @Max(10) private Integer credits;
    @NotNull private Long departmentId;
    public CourseRequest() {}
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
    public Integer getCredits() { return credits; }
    public void setCredits(Integer credits) { this.credits = credits; }
    public Long getDepartmentId() { return departmentId; }
    public void setDepartmentId(Long departmentId) { this.departmentId = departmentId; }
}
