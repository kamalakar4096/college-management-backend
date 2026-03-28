package com.college.management.service;
import com.college.management.dto.request.LoginRequest;
import com.college.management.dto.response.AuthResponse;
public interface AuthService {
    AuthResponse login(LoginRequest request);
}
