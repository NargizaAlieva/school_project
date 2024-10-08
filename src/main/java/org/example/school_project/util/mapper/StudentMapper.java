package org.example.school_project.util.mapper;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.StudentDto;
import org.example.school_project.dto.StudentDtoRequest;
import org.example.school_project.entity.Student;
import org.example.school_project.service.GradeService;
import org.example.school_project.service.ParentService;
import org.example.school_project.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StudentMapper {
    private final UserMapper userMapper;
    private final UserService userService;
    private final GradeService gradeService;
    private final ParentService parentService;

    public StudentDto entityToDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setBirthday(student.getBirthday());
        studentDto.setGradeTitle(student.getGrade().getTitle());
        studentDto.setParentStatus(student.getParentStatus());
        studentDto.setParentId(student.getParent().getId());
        studentDto.setUser(userMapper.entityToDto(student.getUser()));
        return studentDto;
    }

    public List<StudentDto> entityToDtoList(List<Student> students) {
        return students.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public Student dtoToEntity (StudentDtoRequest studentDtoRequest) {
        Student student = new Student();
        student.setId(studentDtoRequest.getId());
        student.setBirthday(studentDtoRequest.getBirthday());
        student.setParentStatus(studentDtoRequest.getParentStatus());
        student.setUser(userService.getEntityById(studentDtoRequest.getUserId()));
        student.setGrade(gradeService.getByIdEntity(studentDtoRequest.getGradeId()));
        student.setParent(parentService.getByIdEntity(studentDtoRequest.getParentId()));
        return student;
    }
}
