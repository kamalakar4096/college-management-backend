package com.college.management.service.impl;
import com.college.management.dto.request.MarksRequest;
import com.college.management.dto.response.MarksResponse;
import com.college.management.entity.*;
import com.college.management.exception.ResourceNotFoundException;
import com.college.management.repository.*;
import com.college.management.service.MarksService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class MarksServiceImpl implements MarksService {
    private final MarksRepository marksRepository;
    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;
    public MarksServiceImpl(MarksRepository marksRepository, UserRepository userRepository, SubjectRepository subjectRepository) {
        this.marksRepository = marksRepository; this.userRepository = userRepository; this.subjectRepository = subjectRepository;
    }
    @Override @Transactional
    public MarksResponse addMarks(MarksRequest request) {
        User student = userRepository.findById(request.getStudentId()).orElseThrow(() -> new ResourceNotFoundException("User", request.getStudentId()));
        Subject subject = subjectRepository.findById(request.getSubjectId()).orElseThrow(() -> new ResourceNotFoundException("Subject", request.getSubjectId()));
        Optional<Marks> existing = marksRepository.findByStudentIdAndSubjectIdAndExamType(student.getId(), subject.getId(), request.getExamType());
        Marks marks = existing.orElse(new Marks());
        marks.setStudent(student); marks.setSubject(subject); marks.setMarks(request.getMarks()); marks.setExamType(request.getExamType());
        return toResponse(marksRepository.save(marks));
    }
    @Override @Transactional
    public MarksResponse updateMarks(Long id, MarksRequest request) {
        Marks marks = marksRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Marks", id));
        marks.setMarks(request.getMarks());
        return toResponse(marksRepository.save(marks));
    }
    @Override public List<MarksResponse> getByStudent(Long studentId) { return marksRepository.findByStudentId(studentId).stream().map(this::toResponse).collect(Collectors.toList()); }
    @Override public List<MarksResponse> getByStudentAndSubject(Long studentId, Long subjectId) { return marksRepository.findByStudentIdAndSubjectId(studentId, subjectId).stream().map(this::toResponse).collect(Collectors.toList()); }
    private MarksResponse toResponse(Marks m) {
        MarksResponse r = new MarksResponse();
        r.setId(m.getId()); r.setMarks(m.getMarks()); r.setExamType(m.getExamType()); r.setCreatedAt(m.getCreatedAt());
        if (m.getStudent() != null) { r.setStudentId(m.getStudent().getId()); r.setStudentName(m.getStudent().getName()); }
        if (m.getSubject() != null) { r.setSubjectId(m.getSubject().getId()); r.setSubjectName(m.getSubject().getSubjectName()); }
        return r;
    }
}
