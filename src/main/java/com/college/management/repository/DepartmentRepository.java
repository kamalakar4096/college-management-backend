package com.college.management.repository;

import com.college.management.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    boolean existsByDepartmentName(String departmentName);

    Optional<Department> findByDepartmentName(String departmentName);

    Optional<Department> findByHodId(Long hodId);
}
