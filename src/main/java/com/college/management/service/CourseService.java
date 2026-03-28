package com.college.management.service;
import com.college.management.dto.request.CourseRequest;
import com.college.management.dto.response.CourseResponse;
import java.util.List;
public interface CourseService {
    CourseResponse create(CourseRequest request);
    CourseResponse getById(Long id);
    List<CourseResponse> getAll();
    List<CourseResponse> getByDepartment(Long departmentId);
    CourseResponse update(Long id, CourseRequest request);
    void delete(Long id);
}
