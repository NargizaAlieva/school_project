package org.example.school_project.service;

import org.example.school_project.dto.StudentDto;
import org.example.school_project.dto.StudentDtoRequest;

public interface StudentService {
    StudentDto createStudent(StudentDtoRequest studentDtoRequest);
    StudentDto updateStudent(StudentDtoRequest studentDtoRequest);
}
