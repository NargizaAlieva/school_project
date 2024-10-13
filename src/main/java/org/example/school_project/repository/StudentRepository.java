package org.example.school_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.example.school_project.entity.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsById(Long id);
}
