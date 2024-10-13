package org.example.school_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.example.school_project.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    boolean existsById(Long id);
}
