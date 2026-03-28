package com.college.management.service.impl;
import com.college.management.dto.request.AssignmentRequest;
import com.college.management.dto.response.AssignmentResponse;
import com.college.management.dto.response.SubmissionResponse;
import com.college.management.entity.*;
import com.college.management.exception.BadRequestException;
import com.college.management.exception.ResourceNotFoundException;
import com.college.management.repository.*;
import com.college.management.service.AssignmentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
public class AssignmentServiceImpl implements AssignmentService {
    private final AssignmentRepository assignmentRepository;
    private final AssignmentSubmissionRepository submissionRepository;
    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;
    @Value("${app.upload.dir}") private String uploadDir;
    public AssignmentServiceImpl(AssignmentRepository assignmentRepository, AssignmentSubmissionRepository submissionRepository, UserRepository userRepository, SubjectRepository subjectRepository) {
        this.assignmentRepository = assignmentRepository; this.submissionRepository = submissionRepository;
        this.userRepository = userRepository; this.subjectRepository = subjectRepository;
    }
    @Override @Transactional
    public AssignmentResponse create(AssignmentRequest request, MultipartFile file, Long facultyId) {
        User faculty = userRepository.findById(facultyId).orElseThrow(() -> new ResourceNotFoundException("User", facultyId));
        Assignment assignment = new Assignment();
        assignment.setTitle(request.getTitle()); assignment.setDescription(request.getDescription());
        assignment.setDeadline(request.getDeadline()); assignment.setFaculty(faculty);
        if (request.getSubjectId() != null) {
            Subject subject = subjectRepository.findById(request.getSubjectId()).orElseThrow(() -> new ResourceNotFoundException("Subject", request.getSubjectId()));
            assignment.setSubject(subject);
        }
        if (file != null && !file.isEmpty()) assignment.setFileUrl(saveFile(file));
        return toResponse(assignmentRepository.save(assignment));
    }
    @Override public AssignmentResponse getById(Long id) { return toResponse(assignmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Assignment", id))); }
    @Override public List<AssignmentResponse> getByFaculty(Long facultyId) { return assignmentRepository.findByFacultyId(facultyId).stream().map(this::toResponse).collect(Collectors.toList()); }
    @Override public List<AssignmentResponse> getBySubject(Long subjectId) { return assignmentRepository.findBySubjectId(subjectId).stream().map(this::toResponse).collect(Collectors.toList()); }
    @Override @Transactional
    public SubmissionResponse submitAssignment(Long assignmentId, Long studentId, MultipartFile file) {
        Assignment assignment = assignmentRepository.findById(assignmentId).orElseThrow(() -> new ResourceNotFoundException("Assignment", assignmentId));
        User student = userRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("User", studentId));
        if (submissionRepository.existsByAssignmentIdAndStudentId(assignmentId, studentId))
            throw new BadRequestException("Assignment already submitted");
        if (file == null || file.isEmpty()) throw new BadRequestException("Submission file is required");
        AssignmentSubmission submission = new AssignmentSubmission();
        submission.setAssignment(assignment); submission.setStudent(student); submission.setFileUrl(saveFile(file));
        submission.setStatus(java.time.LocalDateTime.now().isAfter(assignment.getDeadline())
                ? AssignmentSubmission.SubmissionStatus.LATE : AssignmentSubmission.SubmissionStatus.SUBMITTED);
        return toSubmissionResponse(submissionRepository.save(submission));
    }
    @Override public List<SubmissionResponse> getSubmissions(Long assignmentId) {
        return submissionRepository.findByAssignmentId(assignmentId).stream().map(this::toSubmissionResponse).collect(Collectors.toList());
    }
    @Override public SubmissionResponse getMySubmission(Long assignmentId, Long studentId) {
        AssignmentSubmission sub = submissionRepository.findByAssignmentIdAndStudentId(assignmentId, studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Submission not found"));
        return toSubmissionResponse(sub);
    }
    private String saveFile(MultipartFile file) {
        try {
            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path path = Paths.get(uploadDir, filename);
            Files.createDirectories(path.getParent());
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            return "/uploads/" + filename;
        } catch (IOException e) { throw new RuntimeException("Failed to store file", e); }
    }
    private AssignmentResponse toResponse(Assignment a) {
        AssignmentResponse r = new AssignmentResponse();
        r.setId(a.getId()); r.setTitle(a.getTitle()); r.setDescription(a.getDescription());
        r.setFileUrl(a.getFileUrl()); r.setDeadline(a.getDeadline()); r.setCreatedAt(a.getCreatedAt());
        if (a.getFaculty() != null) { r.setFacultyId(a.getFaculty().getId()); r.setFacultyName(a.getFaculty().getName()); }
        if (a.getSubject() != null) { r.setSubjectId(a.getSubject().getId()); r.setSubjectName(a.getSubject().getSubjectName()); }
        if (a.getSubmissions() != null) r.setTotalSubmissions(a.getSubmissions().size());
        return r;
    }
    private SubmissionResponse toSubmissionResponse(AssignmentSubmission s) {
        SubmissionResponse r = new SubmissionResponse();
        r.setId(s.getId()); r.setFileUrl(s.getFileUrl()); r.setRemarks(s.getRemarks());
        r.setStatus(s.getStatus()); r.setSubmittedAt(s.getSubmittedAt());
        if (s.getAssignment() != null) { r.setAssignmentId(s.getAssignment().getId()); r.setAssignmentTitle(s.getAssignment().getTitle()); }
        if (s.getStudent() != null) { r.setStudentId(s.getStudent().getId()); r.setStudentName(s.getStudent().getName()); }
        return r;
    }
}
