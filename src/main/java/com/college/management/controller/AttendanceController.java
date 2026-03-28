package com.college.management.controller;
import com.college.management.dto.request.AttendanceRequest;
import com.college.management.dto.response.ApiResponse;
import com.college.management.dto.response.AttendanceResponse;
import com.college.management.service.AttendanceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {
    private final AttendanceService attendanceService;
    public AttendanceController(AttendanceService attendanceService) { this.attendanceService = attendanceService; }
    @PostMapping public ResponseEntity<ApiResponse<List<AttendanceResponse>>> mark(@Valid @RequestBody AttendanceRequest request) { return ResponseEntity.ok(ApiResponse.success("Attendance marked", attendanceService.markAttendance(request))); }
    @PutMapping("/{id}") public ResponseEntity<ApiResponse<AttendanceResponse>> update(@PathVariable Long id, @RequestParam String status) { return ResponseEntity.ok(ApiResponse.success("Attendance updated", attendanceService.updateAttendance(id, status))); }
    @GetMapping("/student/{studentId}") public ResponseEntity<ApiResponse<List<AttendanceResponse>>> getByStudent(@PathVariable Long studentId) { return ResponseEntity.ok(ApiResponse.success(attendanceService.getByStudent(studentId))); }
    @GetMapping("/subject/{subjectId}") public ResponseEntity<ApiResponse<List<AttendanceResponse>>> getBySubjectAndDate(@PathVariable Long subjectId, @RequestParam String date) { return ResponseEntity.ok(ApiResponse.success(attendanceService.getBySubjectAndDate(subjectId, date))); }
    @GetMapping("/percentage") public ResponseEntity<ApiResponse<Double>> getPercentage(@RequestParam Long studentId, @RequestParam Long subjectId) { return ResponseEntity.ok(ApiResponse.success(attendanceService.getAttendancePercentage(studentId, subjectId))); }
}
