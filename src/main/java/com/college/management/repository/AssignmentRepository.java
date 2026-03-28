package com.college.management.repository;

import com.college.management.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    List<Assignment> findByFacultyId(Long facultyId);

    List<Assignment> findBySubjectId(Long subjectId);
}
