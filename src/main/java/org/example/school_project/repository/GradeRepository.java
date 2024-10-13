package org.example.school_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.example.school_project.entity.Grade;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    boolean existsById(Long id);
}
