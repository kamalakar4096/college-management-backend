package com.college.management.repository;

import com.college.management.entity.Marks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarksRepository extends JpaRepository<Marks, Long> {

    List<Marks> findByStudentId(Long studentId);

    List<Marks> findBySubjectId(Long subjectId);

    List<Marks> findByStudentIdAndSubjectId(Long studentId, Long subjectId);

    Optional<Marks> findByStudentIdAndSubjectIdAndExamType(Long studentId, Long subjectId, Marks.ExamType examType);

    @Query("SELECT AVG(m.marks) FROM Marks m WHERE m.student.id = :studentId AND m.subject.id = :subjectId")
    Double findAverageMarksByStudentAndSubject(@Param("studentId") Long studentId, @Param("subjectId") Long subjectId);
}
