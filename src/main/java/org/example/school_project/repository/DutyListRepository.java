package org.example.school_project.repository;

import org.example.school_project.entity.DutyList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DutyListRepository extends JpaRepository<DutyList, Long> {
    boolean existsById(Long id);
}
