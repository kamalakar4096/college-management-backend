package com.college.management.controller;
import com.college.management.dto.request.DepartmentRequest;
import com.college.management.dto.response.ApiResponse;
import com.college.management.dto.response.DepartmentResponse;
import com.college.management.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    private final DepartmentService departmentService;
    public DepartmentController(DepartmentService departmentService) { this.departmentService = departmentService; }
    @PostMapping public ResponseEntity<ApiResponse<DepartmentResponse>> create(@Valid @RequestBody DepartmentRequest req) { return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Department created", departmentService.create(req))); }
    @GetMapping public ResponseEntity<ApiResponse<List<DepartmentResponse>>> getAll() { return ResponseEntity.ok(ApiResponse.success(departmentService.getAll())); }
    @GetMapping("/{id}") public ResponseEntity<ApiResponse<DepartmentResponse>> getById(@PathVariable Long id) { return ResponseEntity.ok(ApiResponse.success(departmentService.getById(id))); }
    @PutMapping("/{id}") public ResponseEntity<ApiResponse<DepartmentResponse>> update(@PathVariable Long id, @Valid @RequestBody DepartmentRequest req) { return ResponseEntity.ok(ApiResponse.success("Department updated", departmentService.update(id, req))); }
    @PutMapping("/{id}/assign-hod/{hodId}") public ResponseEntity<ApiResponse<DepartmentResponse>> assignHod(@PathVariable Long id, @PathVariable Long hodId) { return ResponseEntity.ok(ApiResponse.success("HOD assigned", departmentService.assignHod(id, hodId))); }
}
