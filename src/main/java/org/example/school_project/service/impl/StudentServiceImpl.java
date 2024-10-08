package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.StudentDto;
import org.example.school_project.dto.StudentDtoRequest;
import org.example.school_project.entity.Student;
import org.example.school_project.repository.StudentRepository;
import org.example.school_project.service.StudentService;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.example.school_project.util.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    public Student getStudentByIdEntity(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Student"));
    }

    @Override
    public StudentDto getStudentById(Long id) {
        return studentMapper.entityToDto(getStudentByIdEntity(id));
    }

    @Override
    public StudentDto createStudent(StudentDtoRequest studentDtoRequest) {
        return studentMapper.entityToDto(save(studentMapper.dtoToEntity(studentDtoRequest)));
    }

    @Override
    public StudentDto updateStudent(StudentDtoRequest studentDtoRequest) {
        Student oldStudent = studentMapper.dtoToEntity(studentDtoRequest);
        Student newStudent = getStudentByIdEntity(studentDtoRequest.getId());

        newStudent.setId(oldStudent.getId());
        newStudent.setBirthday(oldStudent.getBirthday());
        newStudent.setParentStatus(oldStudent.getParentStatus());
        newStudent.setParent(oldStudent.getParent());
        newStudent.setGrade(oldStudent.getGrade());
        newStudent.setUser(oldStudent.getUser());
        return studentMapper.entityToDto(save(newStudent));
    }

    @Override
    public StudentDto excludeStudent(Long id) {
        Student student = getStudentByIdEntity(id);
        student.getUser().setIsActive(false);
        return studentMapper.entityToDto(save(student));
    }

    @Override
    public List<StudentDto> getAllStudent() {
        return studentMapper.entityToDtoList(studentRepository.findAll());
    }

    @Override
    public List<StudentDto> getAllActiveStudent() {
        List<StudentDto> activeStudents = new ArrayList<>();
        for (StudentDto s : getAllStudent()) {
            if (s.getUser().getIsActive()) activeStudents.add(s);
        }
        return activeStudents;
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }
}
