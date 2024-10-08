package org.example.school_project.service;

import org.apache.catalina.LifecycleState;
import org.example.school_project.dto.StudentDto;
import org.example.school_project.dto.StudentDtoRequest;

import java.util.List;

public interface StudentService {
    StudentDto getStudentById(Long id);
    StudentDto createStudent(StudentDtoRequest studentDtoRequest);
    StudentDto updateStudent(StudentDtoRequest studentDtoRequest);
    StudentDto excludeStudent(Long id);
    List<StudentDto> getAllStudent();
    List<StudentDto> getAllActiveStudent();
}
