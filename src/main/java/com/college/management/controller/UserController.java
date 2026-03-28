package com.college.management.controller;

import com.college.management.dto.request.CreateUserRequest;
import com.college.management.dto.request.UpdateUserRequest;
import com.college.management.dto.response.ApiResponse;
import com.college.management.dto.response.UserResponse;
import com.college.management.entity.Role;
import com.college.management.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.college.management.entity.Role;
import com.college.management.entity.User;
import com.college.management.exception.BadRequestException;
import com.college.management.repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<UserResponse>> create(@Valid @RequestBody CreateUserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("User created", userService.createUser(request)));
    }
    @PostMapping("/dept-create")
    @PreAuthorize("hasAnyRole('HOD', 'FACULTY')")
    public ResponseEntity<ApiResponse<UserResponse>> hodCreate(
            @Valid @RequestBody CreateUserRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        User hod = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("HOD not found"));
        // Force department to HOD's department
        request.setDepartmentId(hod.getDepartment().getId());
        // Only allow FACULTY and STUDENT roles
        if (request.getRole() != Role.STUDENT)
            throw new BadRequestException("Faculty can only create STUDENT");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("User created", userService.createUser(request)));
    }
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success(userService.getAllUsers()));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'HOD')")
    public ResponseEntity<ApiResponse<UserResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(userService.getUserById(id)));
    }

    @GetMapping("/role/{role}")
    @PreAuthorize("hasAnyRole('ADMIN', 'HOD')")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getByRole(@PathVariable Role role) {
        return ResponseEntity.ok(ApiResponse.success(userService.getUsersByRole(role)));
    }

    @GetMapping("/department/{departmentId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'HOD')")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getByDept(@PathVariable Long departmentId) {
        return ResponseEntity.ok(ApiResponse.success(userService.getUsersByDepartment(departmentId)));
    }

    @GetMapping("/department/{departmentId}/faculty")
    @PreAuthorize("hasAnyRole('ADMIN', 'HOD')")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getFacultyByDept(@PathVariable Long departmentId) {
        return ResponseEntity.ok(ApiResponse.success(userService.getFacultyByDepartment(departmentId)));
    }
    @GetMapping("/department/{departmentId}/students")
    @PreAuthorize("hasAnyRole('ADMIN', 'HOD', 'FACULTY')")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getStudentsByDept(
            @PathVariable Long departmentId) {
        return ResponseEntity.ok(ApiResponse.success(
            userService.getStudentsByDepartment(departmentId)));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<UserResponse>> update(@PathVariable Long id,
            @Valid @RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok(ApiResponse.success("User updated", userService.updateUser(id, request)));
    }

    @PutMapping("/{id}/deactivate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<UserResponse>> deactivate(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("User deactivated", userService.deactivateUser(id)));
    }

    @PutMapping("/{id}/activate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<UserResponse>> activate(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("User activated", userService.activateUser(id)));
    }
    
}
