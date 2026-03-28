package com.college.management.controller;

import com.college.management.dto.request.SubjectRequest;
import com.college.management.dto.response.ApiResponse;
import com.college.management.dto.response.SubjectResponse;
import com.college.management.entity.User;
import com.college.management.repository.UserRepository;
import com.college.management.service.SubjectService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    private final SubjectService subjectService;
    private final UserRepository userRepository;

    public SubjectController(SubjectService subjectService, UserRepository userRepository) {
        this.subjectService = subjectService;
        this.userRepository = userRepository;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'HOD')")
    public ResponseEntity<ApiResponse<SubjectResponse>> create(@Valid @RequestBody SubjectRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Subject created", subjectService.create(req)));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'HOD', 'FACULTY', 'DEAN', 'STUDENT')")
    public ResponseEntity<ApiResponse<List<SubjectResponse>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success(subjectService.getAll()));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'HOD', 'FACULTY', 'DEAN', 'STUDENT')")
    public ResponseEntity<ApiResponse<SubjectResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(subjectService.getById(id)));
    }

    @GetMapping("/department/{departmentId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'HOD', 'FACULTY', 'DEAN', 'STUDENT')")
    public ResponseEntity<ApiResponse<List<SubjectResponse>>> getByDept(@PathVariable Long departmentId) {
        return ResponseEntity.ok(ApiResponse.success(subjectService.getByDepartment(departmentId)));
    }

    @GetMapping("/faculty/{facultyId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'HOD', 'FACULTY')")
    public ResponseEntity<ApiResponse<List<SubjectResponse>>> getByFaculty(@PathVariable Long facultyId) {
        return ResponseEntity.ok(ApiResponse.success(subjectService.getByFaculty(facultyId)));
    }

    // ✅ NEW — Faculty gets their own subjects (for dropdown)
    @GetMapping("/my-subjects")
    @PreAuthorize("hasRole('FACULTY')")
    public ResponseEntity<ApiResponse<List<SubjectResponse>>> getMySubjects(
            @AuthenticationPrincipal UserDetails userDetails) {
        User faculty = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(ApiResponse.success(
                subjectService.getByFaculty(faculty.getId())));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'HOD')")
    public ResponseEntity<ApiResponse<SubjectResponse>> update(@PathVariable Long id,
            @Valid @RequestBody SubjectRequest req) {
        return ResponseEntity.ok(ApiResponse.success("Subject updated", subjectService.update(id, req)));
    }

    @PutMapping("/{id}/assign-faculty/{facultyId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'HOD')")
    public ResponseEntity<ApiResponse<SubjectResponse>> assignFaculty(@PathVariable Long id,
            @PathVariable Long facultyId) {
        return ResponseEntity.ok(ApiResponse.success("Faculty assigned",
                subjectService.assignFaculty(id, facultyId)));
    }
}
