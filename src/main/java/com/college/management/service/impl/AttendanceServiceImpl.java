package com.college.management.service.impl;
import com.college.management.dto.request.AttendanceRequest;
import com.college.management.dto.response.AttendanceResponse;
import com.college.management.entity.*;
import com.college.management.exception.ResourceNotFoundException;
import com.college.management.repository.*;
import com.college.management.service.AttendanceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;
    public AttendanceServiceImpl(AttendanceRepository attendanceRepository, UserRepository userRepository, SubjectRepository subjectRepository) {
        this.attendanceRepository = attendanceRepository; this.userRepository = userRepository; this.subjectRepository = subjectRepository;
    }
    @Override @Transactional
    public List<AttendanceResponse> markAttendance(AttendanceRequest request) {
        Subject subject = subjectRepository.findById(request.getSubjectId()).orElseThrow(() -> new ResourceNotFoundException("Subject", request.getSubjectId()));
        List<Attendance> saved = new ArrayList<>();
        for (AttendanceRequest.AttendanceEntry entry : request.getEntries()) {
            User student = userRepository.findById(entry.getStudentId()).orElseThrow(() -> new ResourceNotFoundException("User", entry.getStudentId()));
            Optional<Attendance> existing = attendanceRepository.findByStudentIdAndSubjectIdAndDate(student.getId(), subject.getId(), request.getDate());
            Attendance attendance = existing.orElse(new Attendance());
            attendance.setStudent(student); attendance.setSubject(subject);
            attendance.setDate(request.getDate()); attendance.setStatus(entry.getStatus());
            saved.add(attendanceRepository.save(attendance));
        }
        return saved.stream().map(this::toResponse).collect(Collectors.toList());
    }
    @Override @Transactional
    public AttendanceResponse updateAttendance(Long id, String status) {
        Attendance attendance = attendanceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Attendance", id));
        attendance.setStatus(Attendance.AttendanceStatus.valueOf(status.toUpperCase()));
        return toResponse(attendanceRepository.save(attendance));
    }
    @Override public List<AttendanceResponse> getByStudent(Long studentId) {
        return attendanceRepository.findByStudentId(studentId).stream().map(this::toResponse).collect(Collectors.toList());
    }
    @Override public List<AttendanceResponse> getBySubjectAndDate(Long subjectId, String date) {
        return attendanceRepository.findBySubjectIdAndDate(subjectId, LocalDate.parse(date)).stream().map(this::toResponse).collect(Collectors.toList());
    }
    @Override public double getAttendancePercentage(Long studentId, Long subjectId) {
        long total = attendanceRepository.countTotalByStudentAndSubject(studentId, subjectId);
        if (total == 0) return 0.0;
        long present = attendanceRepository.countPresentByStudentAndSubject(studentId, subjectId);
        return Math.round((present * 100.0 / total) * 100.0) / 100.0;
    }
    private AttendanceResponse toResponse(Attendance a) {
        AttendanceResponse r = new AttendanceResponse();
        r.setId(a.getId()); r.setDate(a.getDate()); r.setStatus(a.getStatus()); r.setCreatedAt(a.getCreatedAt());
        if (a.getStudent() != null) { r.setStudentId(a.getStudent().getId()); r.setStudentName(a.getStudent().getName()); }
        if (a.getSubject() != null) { r.setSubjectId(a.getSubject().getId()); r.setSubjectName(a.getSubject().getSubjectName()); }
        return r;
    }
}
