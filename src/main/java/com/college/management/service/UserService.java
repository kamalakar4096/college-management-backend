package com.college.management.service;

import com.college.management.dto.request.CreateUserRequest;
import com.college.management.dto.request.UpdateUserRequest;
import com.college.management.dto.response.UserResponse;
import com.college.management.entity.Role;
import java.util.List;

public interface UserService {
    UserResponse createUser(CreateUserRequest request);
    UserResponse getUserById(Long id);
    UserResponse getUserByEmail(String email);
    List<UserResponse> getAllUsers();
    List<UserResponse> getUsersByRole(Role role);
    List<UserResponse> getUsersByDepartment(Long departmentId);
    List<UserResponse> getFacultyByDepartment(Long departmentId);
    List<UserResponse> getStudentsByDepartment(Long departmentId);
    UserResponse updateUser(Long id, UpdateUserRequest request);
    UserResponse deactivateUser(Long id);
    UserResponse activateUser(Long id);
}