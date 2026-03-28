package com.college.management.repository;

import com.college.management.entity.Notification;
import com.college.management.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findBySenderId(Long senderId);

    List<Notification> findByTargetRole(Role targetRole);

    @Query("SELECT n FROM Notification n WHERE n.targetRole = :role OR n.targetRole IS NULL ORDER BY n.createdAt DESC")
    List<Notification> findNotificationsForRole(@Param("role") Role role);

    @Query("SELECT n FROM Notification n WHERE (n.targetRole = :role OR n.targetRole IS NULL) AND (n.targetDepartment.id = :deptId OR n.targetDepartment IS NULL) ORDER BY n.createdAt DESC")
    List<Notification> findNotificationsForRoleAndDepartment(@Param("role") Role role, @Param("deptId") Long deptId);
}
