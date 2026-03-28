package com.college.management.dto.response;
import com.college.management.entity.AssignmentSubmission.SubmissionStatus;
import java.time.LocalDateTime;
public class SubmissionResponse {
    private Long id; private Long assignmentId; private String assignmentTitle;
    private Long studentId; private String studentName; private String fileUrl;
    private String remarks; private SubmissionStatus status; private LocalDateTime submittedAt;
    public SubmissionResponse() {}
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public Long getAssignmentId() { return assignmentId; } public void setAssignmentId(Long a) { this.assignmentId = a; }
    public String getAssignmentTitle() { return assignmentTitle; } public void setAssignmentTitle(String a) { this.assignmentTitle = a; }
    public Long getStudentId() { return studentId; } public void setStudentId(Long s) { this.studentId = s; }
    public String getStudentName() { return studentName; } public void setStudentName(String s) { this.studentName = s; }
    public String getFileUrl() { return fileUrl; } public void setFileUrl(String f) { this.fileUrl = f; }
    public String getRemarks() { return remarks; } public void setRemarks(String r) { this.remarks = r; }
    public SubmissionStatus getStatus() { return status; } public void setStatus(SubmissionStatus s) { this.status = s; }
    public LocalDateTime getSubmittedAt() { return submittedAt; } public void setSubmittedAt(LocalDateTime s) { this.submittedAt = s; }
}
