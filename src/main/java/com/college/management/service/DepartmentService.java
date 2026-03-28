package com.college.management.service;
import com.college.management.dto.request.DepartmentRequest;
import com.college.management.dto.response.DepartmentResponse;
import java.util.List;
public interface DepartmentService {
    DepartmentResponse create(DepartmentRequest request);
    DepartmentResponse getById(Long id);
    List<DepartmentResponse> getAll();
    DepartmentResponse update(Long id, DepartmentRequest request);
    DepartmentResponse assignHod(Long departmentId, Long hodId);
}
