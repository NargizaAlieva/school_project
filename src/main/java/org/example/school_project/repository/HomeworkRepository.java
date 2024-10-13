package org.example.school_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.example.school_project.entity.Homework;

@Repository
public interface HomeworkRepository extends JpaRepository<Homework, Long> {
    boolean existsById(Long id);
}
