package org.example.school_project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.example.school_project.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    boolean existsById(Long id);
    boolean existsByTitle(String title);
    Optional<Subject> findByTitle(String title);
}
