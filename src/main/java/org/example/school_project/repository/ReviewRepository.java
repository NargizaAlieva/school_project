package org.example.school_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.example.school_project.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    boolean existsById(Long id);
}
