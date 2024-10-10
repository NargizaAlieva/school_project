package org.example.school_project.repository;

import org.example.school_project.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    boolean existsById(Long id);
}
