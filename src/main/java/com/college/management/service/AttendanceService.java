package com.college.management.service;
import com.college.management.dto.request.AttendanceRequest;
import com.college.management.dto.response.AttendanceResponse;
import java.util.List;
public interface AttendanceService {
    List<AttendanceResponse> markAttendance(AttendanceRequest request);
    AttendanceResponse updateAttendance(Long id, String status);
    List<AttendanceResponse> getByStudent(Long studentId);
    List<AttendanceResponse> getBySubjectAndDate(Long subjectId, String date);
    double getAttendancePercentage(Long studentId, Long subjectId);
}
