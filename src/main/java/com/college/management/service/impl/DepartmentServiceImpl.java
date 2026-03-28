package com.college.management.service.impl;
import com.college.management.dto.request.DepartmentRequest;
import com.college.management.dto.response.DepartmentResponse;
import com.college.management.entity.Department;
import com.college.management.entity.Role;
import com.college.management.entity.User;
import com.college.management.exception.BadRequestException;
import com.college.management.exception.ResourceNotFoundException;
import com.college.management.repository.DepartmentRepository;
import com.college.management.repository.UserRepository;
import com.college.management.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;
    public DepartmentServiceImpl(DepartmentRepository departmentRepository, UserRepository userRepository) {
        this.departmentRepository = departmentRepository; this.userRepository = userRepository;
    }
    @Override @Transactional
    public DepartmentResponse create(DepartmentRequest request) {
        if (departmentRepository.existsByDepartmentName(request.getDepartmentName()))
            throw new BadRequestException("Department already exists: " + request.getDepartmentName());
        Department dept = new Department();
        dept.setDepartmentName(request.getDepartmentName());
        if (request.getHodId() != null) {
            User hod = userRepository.findById(request.getHodId())
                    .orElseThrow(() -> new ResourceNotFoundException("User", request.getHodId()));
            if (hod.getRole() != Role.HOD) throw new BadRequestException("User is not an HOD");
            dept.setHod(hod);
        }
        return toResponse(departmentRepository.save(dept));
    }
    @Override public DepartmentResponse getById(Long id) {
        return toResponse(departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department", id)));
    }
    @Override public List<DepartmentResponse> getAll() {
        return departmentRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }
    @Override @Transactional
    public DepartmentResponse update(Long id, DepartmentRequest request) {
        Department dept = departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department", id));
        dept.setDepartmentName(request.getDepartmentName());
        if (request.getHodId() != null) {
            User hod = userRepository.findById(request.getHodId())
                    .orElseThrow(() -> new ResourceNotFoundException("User", request.getHodId()));
            if (hod.getRole() != Role.HOD) throw new BadRequestException("User is not an HOD");
            dept.setHod(hod);
        }
        return toResponse(departmentRepository.save(dept));
    }
    @Override @Transactional
    public DepartmentResponse assignHod(Long departmentId, Long hodId) {
        Department dept = departmentRepository.findById(departmentId).orElseThrow(() -> new ResourceNotFoundException("Department", departmentId));
        User hod = userRepository.findById(hodId).orElseThrow(() -> new ResourceNotFoundException("User", hodId));
        if (hod.getRole() != Role.HOD) throw new BadRequestException("User is not an HOD");
        dept.setHod(hod);
        return toResponse(departmentRepository.save(dept));
    }
    private DepartmentResponse toResponse(Department d) {
        DepartmentResponse r = new DepartmentResponse();
        r.setId(d.getId()); r.setDepartmentName(d.getDepartmentName()); r.setCreatedAt(d.getCreatedAt());
        if (d.getHod() != null) { r.setHodId(d.getHod().getId()); r.setHodName(d.getHod().getName()); r.setHodEmail(d.getHod().getEmail()); }
        if (d.getMembers() != null) r.setTotalMembers(d.getMembers().size());
        return r;
    }
}
