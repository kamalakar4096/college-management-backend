package com.college.management.controller;
import com.college.management.dto.request.MarksRequest;
import com.college.management.dto.response.ApiResponse;
import com.college.management.dto.response.MarksResponse;
import com.college.management.service.MarksService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/marks")
public class MarksController {
    private final MarksService marksService;
    public MarksController(MarksService marksService) { this.marksService = marksService; }
    @PostMapping public ResponseEntity<ApiResponse<MarksResponse>> add(@Valid @RequestBody MarksRequest request) { return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Marks added", marksService.addMarks(request))); }
    @PutMapping("/{id}") public ResponseEntity<ApiResponse<MarksResponse>> update(@PathVariable Long id, @Valid @RequestBody MarksRequest request) { return ResponseEntity.ok(ApiResponse.success("Marks updated", marksService.updateMarks(id, request))); }
    @GetMapping("/student/{studentId}") public ResponseEntity<ApiResponse<List<MarksResponse>>> getByStudent(@PathVariable Long studentId) { return ResponseEntity.ok(ApiResponse.success(marksService.getByStudent(studentId))); }
    @GetMapping("/student/{studentId}/subject/{subjectId}") public ResponseEntity<ApiResponse<List<MarksResponse>>> getByStudentAndSubject(@PathVariable Long studentId, @PathVariable Long subjectId) { return ResponseEntity.ok(ApiResponse.success(marksService.getByStudentAndSubject(studentId, subjectId))); }
}
