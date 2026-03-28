package com.college.management.service.impl;
import com.college.management.dto.request.CourseRequest;
import com.college.management.dto.response.CourseResponse;
import com.college.management.entity.Course;
import com.college.management.entity.Department;
import com.college.management.exception.BadRequestException;
import com.college.management.exception.ResourceNotFoundException;
import com.college.management.repository.CourseRepository;
import com.college.management.repository.DepartmentRepository;
import com.college.management.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final DepartmentRepository departmentRepository;
    public CourseServiceImpl(CourseRepository courseRepository, DepartmentRepository departmentRepository) {
        this.courseRepository = courseRepository; this.departmentRepository = departmentRepository;
    }
    @Override @Transactional
    public CourseResponse create(CourseRequest request) {
        if (courseRepository.existsByCourseCode(request.getCourseCode()))
            throw new BadRequestException("Course code already exists: " + request.getCourseCode());
        Department dept = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department", request.getDepartmentId()));
        Course course = new Course();
        course.setCourseName(request.getCourseName()); course.setCourseCode(request.getCourseCode());
        course.setCredits(request.getCredits()); course.setDepartment(dept);
        return toResponse(courseRepository.save(course));
    }
    @Override public CourseResponse getById(Long id) {
        return toResponse(courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course", id)));
    }
    @Override public List<CourseResponse> getAll() { return courseRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList()); }
    @Override public List<CourseResponse> getByDepartment(Long departmentId) { return courseRepository.findByDepartmentId(departmentId).stream().map(this::toResponse).collect(Collectors.toList()); }
    @Override @Transactional
    public CourseResponse update(Long id, CourseRequest request) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course", id));
        course.setCourseName(request.getCourseName()); course.setCourseCode(request.getCourseCode()); course.setCredits(request.getCredits());
        if (request.getDepartmentId() != null) {
            Department dept = departmentRepository.findById(request.getDepartmentId()).orElseThrow(() -> new ResourceNotFoundException("Department", request.getDepartmentId()));
            course.setDepartment(dept);
        }
        return toResponse(courseRepository.save(course));
    }
    @Override @Transactional public void delete(Long id) {
        if (!courseRepository.existsById(id)) throw new ResourceNotFoundException("Course", id);
        courseRepository.deleteById(id);
    }
    private CourseResponse toResponse(Course c) {
        CourseResponse r = new CourseResponse();
        r.setId(c.getId()); r.setCourseName(c.getCourseName()); r.setCourseCode(c.getCourseCode());
        r.setCredits(c.getCredits()); r.setCreatedAt(c.getCreatedAt());
        if (c.getDepartment() != null) { r.setDepartmentId(c.getDepartment().getId()); r.setDepartmentName(c.getDepartment().getDepartmentName()); }
        return r;
    }
}
