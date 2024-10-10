package org.example.school_project.repository;

import org.example.school_project.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    boolean existsById(Long id);
}
