package org.example.school_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.example.school_project.entity.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    boolean existsById(Long id);
}
