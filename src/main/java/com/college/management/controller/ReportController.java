package com.college.management.controller;
import com.college.management.dto.response.ApiResponse;
import com.college.management.dto.response.ReportResponse;
import com.college.management.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/reports")
@PreAuthorize("hasAnyRole('ADMIN','DEAN')")
public class ReportController {
    private final ReportService reportService;
    public ReportController(ReportService reportService) { this.reportService = reportService; }
    @GetMapping("/summary") public ResponseEntity<ApiResponse<ReportResponse>> getSummary() { return ResponseEntity.ok(ApiResponse.success(reportService.getSummaryReport())); }
    @GetMapping("/department/{departmentId}") public ResponseEntity<ApiResponse<ReportResponse.DepartmentStats>> getDeptStats(@PathVariable Long departmentId) { return ResponseEntity.ok(ApiResponse.success(reportService.getDepartmentStats(departmentId))); }
}
