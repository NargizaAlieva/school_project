package org.example.school_project.service;

import org.example.school_project.dto.StudentDto;
import org.example.school_project.dto.StudentDtoRequest;
import org.example.school_project.entity.Student;

import java.util.List;

public interface StudentService {
    Student getStudentByIdEntity(Long id);
    StudentDto getStudentById(Long id);
    StudentDto createStudent(StudentDtoRequest studentDtoRequest);
    StudentDto updateStudent(StudentDtoRequest studentDtoRequest);
    StudentDto chooseClassRepresentative(Long id);
    StudentDto getGradeRepresentative(Long gradeId);
    List<StudentDto> getGradeRepresentative(List<Long> gradeId);
    StudentDto excludeStudent(Long id);
    List<StudentDto> getAllStudent();
    List<StudentDto> getAllActiveStudent();
    List<StudentDto> getAllStudentByGrade(Long gradeId);
    List<StudentDto> getAllStudentByGrades(List<Long> gradeId);
}
