package com.college.management.service;
import com.college.management.dto.request.MarksRequest;
import com.college.management.dto.response.MarksResponse;
import java.util.List;
public interface MarksService {
    MarksResponse addMarks(MarksRequest request);
    MarksResponse updateMarks(Long id, MarksRequest request);
    List<MarksResponse> getByStudent(Long studentId);
    List<MarksResponse> getByStudentAndSubject(Long studentId, Long subjectId);
}
