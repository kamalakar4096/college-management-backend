package com.college.management.controller;
import com.college.management.dto.request.AssignmentRequest;
import com.college.management.dto.response.ApiResponse;
import com.college.management.dto.response.AssignmentResponse;
import com.college.management.dto.response.SubmissionResponse;
import com.college.management.security.UserPrincipal;
import com.college.management.service.AssignmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {
    private final AssignmentService assignmentService;
    public AssignmentController(AssignmentService assignmentService) { this.assignmentService = assignmentService; }
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<AssignmentResponse>> create(
            @Valid @RequestPart("data") AssignmentRequest request,
            @RequestPart(value = "file", required = false) MultipartFile file,
            @AuthenticationPrincipal UserPrincipal principal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Assignment created", assignmentService.create(request, file, principal.getId())));
    }
    @GetMapping("/{id}") public ResponseEntity<ApiResponse<AssignmentResponse>> getById(@PathVariable Long id) { return ResponseEntity.ok(ApiResponse.success(assignmentService.getById(id))); }
    @GetMapping("/faculty/{facultyId}") public ResponseEntity<ApiResponse<List<AssignmentResponse>>> getByFaculty(@PathVariable Long facultyId) { return ResponseEntity.ok(ApiResponse.success(assignmentService.getByFaculty(facultyId))); }
    @GetMapping("/subject/{subjectId}") public ResponseEntity<ApiResponse<List<AssignmentResponse>>> getBySubject(@PathVariable Long subjectId) { return ResponseEntity.ok(ApiResponse.success(assignmentService.getBySubject(subjectId))); }
    @PostMapping(value = "/{id}/submit", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<SubmissionResponse>> submit(
            @PathVariable Long id,
            @RequestPart("file") MultipartFile file,
            @AuthenticationPrincipal UserPrincipal principal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Assignment submitted", assignmentService.submitAssignment(id, principal.getId(), file)));
    }
    @GetMapping("/{id}/submissions") public ResponseEntity<ApiResponse<List<SubmissionResponse>>> getSubmissions(@PathVariable Long id) { return ResponseEntity.ok(ApiResponse.success(assignmentService.getSubmissions(id))); }
    @GetMapping("/{id}/my-submission") public ResponseEntity<ApiResponse<SubmissionResponse>> getMySubmission(@PathVariable Long id, @AuthenticationPrincipal UserPrincipal principal) { return ResponseEntity.ok(ApiResponse.success(assignmentService.getMySubmission(id, principal.getId()))); }
}
