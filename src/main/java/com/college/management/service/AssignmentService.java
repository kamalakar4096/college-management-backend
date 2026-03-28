package com.college.management.service;
import com.college.management.dto.request.AssignmentRequest;
import com.college.management.dto.response.AssignmentResponse;
import com.college.management.dto.response.SubmissionResponse;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
public interface AssignmentService {
    AssignmentResponse create(AssignmentRequest request, MultipartFile file, Long facultyId);
    AssignmentResponse getById(Long id);
    List<AssignmentResponse> getByFaculty(Long facultyId);
    List<AssignmentResponse> getBySubject(Long subjectId);
    SubmissionResponse submitAssignment(Long assignmentId, Long studentId, MultipartFile file);
    List<SubmissionResponse> getSubmissions(Long assignmentId);
    SubmissionResponse getMySubmission(Long assignmentId, Long studentId);
}
