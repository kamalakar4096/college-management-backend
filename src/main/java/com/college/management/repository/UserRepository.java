package com.college.management.repository;

import com.college.management.entity.User;
import com.college.management.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    List<User> findByRole(Role role);

    List<User> findByDepartmentId(Long departmentId);

    List<User> findByRoleAndDepartmentId(Role role, Long departmentId);

    List<User> findByActive(boolean active);

    @Query("SELECT COUNT(u) FROM User u WHERE u.role = :role AND u.active = true")
    Long countByRoleAndActive(Role role);

    @Query("SELECT COUNT(u) FROM User u WHERE u.active = true")
    Long countActiveUsers();
}
