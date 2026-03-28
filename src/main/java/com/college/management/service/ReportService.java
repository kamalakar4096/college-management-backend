package com.college.management.service;
import com.college.management.dto.response.ReportResponse;
public interface ReportService {
    ReportResponse getSummaryReport();
    ReportResponse.DepartmentStats getDepartmentStats(Long departmentId);
}
