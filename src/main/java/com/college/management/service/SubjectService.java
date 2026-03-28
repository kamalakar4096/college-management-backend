package com.college.management.service;
import com.college.management.dto.request.SubjectRequest;
import com.college.management.dto.response.SubjectResponse;
import java.util.List;
public interface SubjectService {
    SubjectResponse create(SubjectRequest request);
    SubjectResponse getById(Long id);
    List<SubjectResponse> getAll();
    List<SubjectResponse> getByDepartment(Long departmentId);
    List<SubjectResponse> getByFaculty(Long facultyId);
    SubjectResponse update(Long id, SubjectRequest request);
    SubjectResponse assignFaculty(Long subjectId, Long facultyId);
}
