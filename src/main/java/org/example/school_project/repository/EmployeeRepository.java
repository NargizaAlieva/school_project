package org.example.school_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.example.school_project.entity.Employee;
import org.example.school_project.entity.User;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsById(Long id);
    boolean existsByUser(User user);
}
