package com.college.management.dto.request;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
public class AssignmentRequest {
    @NotBlank private String title;
    private String description;
    private Long subjectId;
    @NotNull @Future private LocalDateTime deadline;
    public AssignmentRequest() {}
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Long getSubjectId() { return subjectId; }
    public void setSubjectId(Long subjectId) { this.subjectId = subjectId; }
    public LocalDateTime getDeadline() { return deadline; }
    public void setDeadline(LocalDateTime deadline) { this.deadline = deadline; }
}
