package com.college.management.dto.response;
import java.util.List;
public class ReportResponse {
    private Long totalStudents; private Long totalFaculty; private Long totalHods;
    private Long totalDeans; private Long totalDepartments; private Long totalCourses;
    private List<DepartmentStats> departmentStats;
    public ReportResponse() {}
    public Long getTotalStudents() { return totalStudents; } public void setTotalStudents(Long t) { this.totalStudents = t; }
    public Long getTotalFaculty() { return totalFaculty; } public void setTotalFaculty(Long t) { this.totalFaculty = t; }
    public Long getTotalHods() { return totalHods; } public void setTotalHods(Long t) { this.totalHods = t; }
    public Long getTotalDeans() { return totalDeans; } public void setTotalDeans(Long t) { this.totalDeans = t; }
    public Long getTotalDepartments() { return totalDepartments; } public void setTotalDepartments(Long t) { this.totalDepartments = t; }
    public Long getTotalCourses() { return totalCourses; } public void setTotalCourses(Long t) { this.totalCourses = t; }
    public List<DepartmentStats> getDepartmentStats() { return departmentStats; } public void setDepartmentStats(List<DepartmentStats> d) { this.departmentStats = d; }
    public static class DepartmentStats {
        private Long departmentId; private String departmentName;
        private long studentCount; private long facultyCount; private long subjectCount; private double averageAttendance;
        public DepartmentStats() {}
        public Long getDepartmentId() { return departmentId; } public void setDepartmentId(Long d) { this.departmentId = d; }
        public String getDepartmentName() { return departmentName; } public void setDepartmentName(String d) { this.departmentName = d; }
        public long getStudentCount() { return studentCount; } public void setStudentCount(long s) { this.studentCount = s; }
        public long getFacultyCount() { return facultyCount; } public void setFacultyCount(long f) { this.facultyCount = f; }
        public long getSubjectCount() { return subjectCount; } public void setSubjectCount(long s) { this.subjectCount = s; }
        public double getAverageAttendance() { return averageAttendance; } public void setAverageAttendance(double a) { this.averageAttendance = a; }
    }
}
