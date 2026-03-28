package com.college.management.service.impl;
import com.college.management.dto.response.ReportResponse;
import com.college.management.entity.Role;
import com.college.management.repository.*;
import com.college.management.service.ReportService;
import com.college.management.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
@Service
public class ReportServiceImpl implements ReportService {
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final CourseRepository courseRepository;
    private final SubjectRepository subjectRepository;
    private final AttendanceRepository attendanceRepository;
    public ReportServiceImpl(UserRepository userRepository, DepartmentRepository departmentRepository, CourseRepository courseRepository, SubjectRepository subjectRepository, AttendanceRepository attendanceRepository) {
        this.userRepository = userRepository; this.departmentRepository = departmentRepository;
        this.courseRepository = courseRepository; this.subjectRepository = subjectRepository; this.attendanceRepository = attendanceRepository;
    }
    @Override
    public ReportResponse getSummaryReport() {
        ReportResponse r = new ReportResponse();
        r.setTotalStudents(userRepository.countByRoleAndActive(Role.STUDENT));
        r.setTotalFaculty(userRepository.countByRoleAndActive(Role.FACULTY));
        r.setTotalHods(userRepository.countByRoleAndActive(Role.HOD));
        r.setTotalDeans(userRepository.countByRoleAndActive(Role.DEAN));
        r.setTotalDepartments((long) departmentRepository.findAll().size());
        r.setTotalCourses((long) courseRepository.findAll().size());
        r.setDepartmentStats(departmentRepository.findAll().stream().map(dept -> {
            ReportResponse.DepartmentStats s = new ReportResponse.DepartmentStats();
            s.setDepartmentId(dept.getId()); s.setDepartmentName(dept.getDepartmentName());
            s.setStudentCount(userRepository.findByRoleAndDepartmentId(Role.STUDENT, dept.getId()).size());
            s.setFacultyCount(userRepository.findByRoleAndDepartmentId(Role.FACULTY, dept.getId()).size());
            s.setSubjectCount(subjectRepository.findByDepartmentId(dept.getId()).size());
            return s;
        }).collect(Collectors.toList()));
        return r;
    }
    @Override
    public ReportResponse.DepartmentStats getDepartmentStats(Long departmentId) {
        var dept = departmentRepository.findById(departmentId).orElseThrow(() -> new ResourceNotFoundException("Department", departmentId));
        ReportResponse.DepartmentStats s = new ReportResponse.DepartmentStats();
        s.setDepartmentId(dept.getId()); s.setDepartmentName(dept.getDepartmentName());
        s.setStudentCount(userRepository.findByRoleAndDepartmentId(Role.STUDENT, departmentId).size());
        s.setFacultyCount(userRepository.findByRoleAndDepartmentId(Role.FACULTY, departmentId).size());
        s.setSubjectCount(subjectRepository.findByDepartmentId(departmentId).size());
        return s;
    }
}
