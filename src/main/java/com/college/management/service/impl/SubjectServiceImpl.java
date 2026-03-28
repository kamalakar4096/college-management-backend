package com.college.management.service.impl;
import com.college.management.dto.request.SubjectRequest;
import com.college.management.dto.response.SubjectResponse;
import com.college.management.entity.*;
import com.college.management.exception.BadRequestException;
import com.college.management.exception.ResourceNotFoundException;
import com.college.management.repository.*;
import com.college.management.service.SubjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;
    public SubjectServiceImpl(SubjectRepository subjectRepository, DepartmentRepository departmentRepository, UserRepository userRepository) {
        this.subjectRepository = subjectRepository; this.departmentRepository = departmentRepository; this.userRepository = userRepository;
    }
    @Override @Transactional
    public SubjectResponse create(SubjectRequest request) {
        Department dept = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department", request.getDepartmentId()));
        Subject subject = new Subject();
        subject.setSubjectName(request.getSubjectName()); subject.setSemester(request.getSemester()); subject.setDepartment(dept);
        if (request.getFacultyId() != null) {
            User faculty = userRepository.findById(request.getFacultyId()).orElseThrow(() -> new ResourceNotFoundException("User", request.getFacultyId()));
            if (faculty.getRole() != Role.FACULTY) throw new BadRequestException("User is not a Faculty member");
            subject.setFaculty(faculty);
        }
        return toResponse(subjectRepository.save(subject));
    }
    @Override public SubjectResponse getById(Long id) { return toResponse(subjectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Subject", id))); }
    @Override public List<SubjectResponse> getAll() { return subjectRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList()); }
    @Override public List<SubjectResponse> getByDepartment(Long departmentId) { return subjectRepository.findByDepartmentId(departmentId).stream().map(this::toResponse).collect(Collectors.toList()); }
    @Override public List<SubjectResponse> getByFaculty(Long facultyId) { return subjectRepository.findByFacultyId(facultyId).stream().map(this::toResponse).collect(Collectors.toList()); }
    @Override @Transactional
    public SubjectResponse update(Long id, SubjectRequest request) {
        Subject subject = subjectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Subject", id));
        subject.setSubjectName(request.getSubjectName()); subject.setSemester(request.getSemester());
        if (request.getFacultyId() != null) {
            User faculty = userRepository.findById(request.getFacultyId()).orElseThrow(() -> new ResourceNotFoundException("User", request.getFacultyId()));
            if (faculty.getRole() != Role.FACULTY) throw new BadRequestException("User is not a Faculty member");
            subject.setFaculty(faculty);
        }
        if (request.getDepartmentId() != null) {
            Department dept = departmentRepository.findById(request.getDepartmentId()).orElseThrow(() -> new ResourceNotFoundException("Department", request.getDepartmentId()));
            subject.setDepartment(dept);
        }
        return toResponse(subjectRepository.save(subject));
    }
    @Override @Transactional
    public SubjectResponse assignFaculty(Long subjectId, Long facultyId) {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new ResourceNotFoundException("Subject", subjectId));
        User faculty = userRepository.findById(facultyId).orElseThrow(() -> new ResourceNotFoundException("User", facultyId));
        if (faculty.getRole() != Role.FACULTY) throw new BadRequestException("User is not a Faculty member");
        subject.setFaculty(faculty);
        return toResponse(subjectRepository.save(subject));
    }
    private SubjectResponse toResponse(Subject s) {
        SubjectResponse r = new SubjectResponse();
        r.setId(s.getId()); r.setSubjectName(s.getSubjectName()); r.setSemester(s.getSemester()); r.setCreatedAt(s.getCreatedAt());
        if (s.getFaculty() != null) { r.setFacultyId(s.getFaculty().getId()); r.setFacultyName(s.getFaculty().getName()); }
        if (s.getDepartment() != null) { r.setDepartmentId(s.getDepartment().getId()); r.setDepartmentName(s.getDepartment().getDepartmentName()); }
        return r;
    }
}
