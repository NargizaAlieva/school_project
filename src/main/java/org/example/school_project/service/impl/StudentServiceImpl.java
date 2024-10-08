package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.StudentDto;
import org.example.school_project.dto.StudentDtoRequest;
import org.example.school_project.entity.Student;
import org.example.school_project.repository.StudentRepository;
import org.example.school_project.service.StudentService;
import org.example.school_project.util.mapper.StudentMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public StudentDto createStudent(StudentDtoRequest studentDtoRequest) {
        return studentMapper.entityToDto(studentRepository.save(studentMapper.dtoToEntity(studentDtoRequest)));
    }

    @Override
    public StudentDto updateStudent(StudentDtoRequest studentDtoRequest) {
        Student oldStudent = studentMapper.dtoToEntity(studentDtoRequest);
        Student newStudent = new Student();

        newStudent.setId(oldStudent.getId());
        newStudent.setBirthday(oldStudent.getBirthday());
        newStudent.setParentStatus(oldStudent.getParentStatus());
        newStudent.setParent(oldStudent.getParent());
        newStudent.setGrade(oldStudent.getGrade());
        newStudent.setUser(oldStudent.getUser());
        return null;
    }
}
