package org.example.school_project.repository;

import org.example.school_project.entity.Charter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharterRepository extends JpaRepository<Charter, Long> {
}
