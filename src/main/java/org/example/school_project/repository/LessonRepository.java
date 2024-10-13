package org.example.school_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.example.school_project.entity.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    boolean existsById(Long id);
}
