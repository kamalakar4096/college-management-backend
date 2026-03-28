package com.college.management.dto.request;
import com.college.management.entity.Attendance.AttendanceStatus;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
public class AttendanceRequest {
    @NotNull private Long subjectId;
    @NotNull private LocalDate date;
    @NotNull private List<AttendanceEntry> entries;
    public AttendanceRequest() {}
    public Long getSubjectId() { return subjectId; }
    public void setSubjectId(Long subjectId) { this.subjectId = subjectId; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public List<AttendanceEntry> getEntries() { return entries; }
    public void setEntries(List<AttendanceEntry> entries) { this.entries = entries; }
    public static class AttendanceEntry {
        @NotNull private Long studentId;
        @NotNull private AttendanceStatus status;
        public AttendanceEntry() {}
        public Long getStudentId() { return studentId; }
        public void setStudentId(Long studentId) { this.studentId = studentId; }
        public AttendanceStatus getStatus() { return status; }
        public void setStatus(AttendanceStatus status) { this.status = status; }
    }
}
