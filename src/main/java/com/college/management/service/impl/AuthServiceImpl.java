package com.college.management.service.impl;
import com.college.management.dto.request.LoginRequest;
import com.college.management.dto.response.AuthResponse;
import com.college.management.entity.User;
import com.college.management.repository.UserRepository;
import com.college.management.security.JwtUtil;
import com.college.management.security.UserPrincipal;
import com.college.management.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
@Service
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager; this.jwtUtil = jwtUtil;
    }
    @Override
    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        User user = userPrincipal.getUser();
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole().name());
        claims.put("userId", user.getId());
        claims.put("departmentId", user.getDepartment() != null ? user.getDepartment().getId() : null);
        String token = jwtUtil.generateTokenWithClaims(user.getEmail(), claims);
        AuthResponse resp = new AuthResponse();
        resp.setAccessToken(token);
        resp.setUserId(user.getId());
        resp.setName(user.getName());
        resp.setEmail(user.getEmail());
        resp.setRole(user.getRole());
        resp.setDepartmentId(user.getDepartment() != null ? user.getDepartment().getId() : null);
        return resp;
    }
}
