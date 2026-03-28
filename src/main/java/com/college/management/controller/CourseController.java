package com.college.management.controller;
import com.college.management.dto.request.CourseRequest;
import com.college.management.dto.response.ApiResponse;
import com.college.management.dto.response.CourseResponse;
import com.college.management.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;
    public CourseController(CourseService courseService) { this.courseService = courseService; }
    @PostMapping public ResponseEntity<ApiResponse<CourseResponse>> create(@Valid @RequestBody CourseRequest req) { return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Course created", courseService.create(req))); }
    @GetMapping public ResponseEntity<ApiResponse<List<CourseResponse>>> getAll() { return ResponseEntity.ok(ApiResponse.success(courseService.getAll())); }
    @GetMapping("/{id}") public ResponseEntity<ApiResponse<CourseResponse>> getById(@PathVariable Long id) { return ResponseEntity.ok(ApiResponse.success(courseService.getById(id))); }
    @GetMapping("/department/{departmentId}") public ResponseEntity<ApiResponse<List<CourseResponse>>> getByDept(@PathVariable Long departmentId) { return ResponseEntity.ok(ApiResponse.success(courseService.getByDepartment(departmentId))); }
    @PutMapping("/{id}") public ResponseEntity<ApiResponse<CourseResponse>> update(@PathVariable Long id, @Valid @RequestBody CourseRequest req) { return ResponseEntity.ok(ApiResponse.success("Course updated", courseService.update(id, req))); }
    @DeleteMapping("/{id}") public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) { courseService.delete(id); return ResponseEntity.ok(ApiResponse.success("Course deleted", null)); }
}
