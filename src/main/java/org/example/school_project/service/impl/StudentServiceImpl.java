package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.RoleDto;
import org.example.school_project.dto.StudentDto;
import org.example.school_project.dto.StudentDtoRequest;
import org.example.school_project.entity.Role;
import org.example.school_project.entity.Student;
import org.example.school_project.entity.User;
import org.example.school_project.repository.StudentRepository;
import org.example.school_project.service.StudentService;
import org.example.school_project.service.UserService;
import org.example.school_project.util.exception.AlreadyExistException;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.example.school_project.util.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final UserService userService;

    public Student save(Student student) {
        return studentRepository.save(student);
    }
    public List<Student> getAllStudentEntity() {
        return studentRepository.findAll();
    }

    public Boolean isExistByUserId(Long id) {
        for (StudentDto e : getAllActiveStudent())
            if (e.getUser().getId().equals(id))
                return true;
        return false;
    }

    @Override
    public Student getStudentByIdEntity(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Student"));
    }

    @Override
    public StudentDto getStudentById(Long id) {
        return studentMapper.entityToDto(getStudentByIdEntity(id));
    }

    @Override
    public Student getStudentByUserId(Long id) {
        for (Student s : getAllStudentEntity()) {
            if (s.getUser().getId().equals(id))
                return s;
        }
        throw new ObjectNotFoundException("Student");
    }

    @Override
    public StudentDto createStudent(StudentDtoRequest studentDtoRequest) {
        if(studentRepository.existsById(studentDtoRequest.getId()))
            throw new AlreadyExistException("Student", "'id'");
        if(isExistByUserId(studentDtoRequest.getUserId()))
            throw new AlreadyExistException("Student", "'userId'");

        return studentMapper.entityToDto(save(studentMapper.dtoToEntity(studentDtoRequest)));
    }

    @Override
    public StudentDto updateStudent(StudentDtoRequest studentDtoRequest) {
        if (userService.getById(studentDtoRequest.getUserId()) == null)
            throw new ObjectNotFoundException("User");

        Student oldStudent = studentMapper.dtoToEntity(studentDtoRequest);
        Student newStudent = getStudentByIdEntity(studentDtoRequest.getId());

        if(!oldStudent.getUser().getId().equals(newStudent.getUser().getId()))
            if (isExistByUserId(studentDtoRequest.getUserId()))
                throw new AlreadyExistException("Student", "'userId'");

        newStudent.setId(oldStudent.getId());
        newStudent.setBirthday(oldStudent.getBirthday());
        newStudent.setParentStatus(oldStudent.getParentStatus());
        newStudent.setParent(oldStudent.getParent());
        newStudent.setGrade(oldStudent.getGrade());
        newStudent.setUser(oldStudent.getUser());
        return studentMapper.entityToDto(save(newStudent));
    }

    @Override
    public StudentDto chooseClassRepresentative(Long id) {
        Set<Long> roleId = new HashSet<>();
        roleId.add(7L);
        RoleDto roleDto = new RoleDto(getStudentByIdEntity(id).getUser().getId(), roleId);
        userService.addRoleToUser(roleDto);
        return studentMapper.entityToDto(getStudentByIdEntity(id));
    }

    @Override
    public StudentDto getGradeRepresentative(Long gradeId) {
        for (StudentDto s : getAllStudentByGrade(gradeId)) {
            Set<Role> roleSet = userService.getEntityById(s.getId()).getRoleSet();
            for (Role r : roleSet)
                if (r.getId() == 7) return s;
        }
        return null;
    }

    @Override
    public List<StudentDto> getGradeRepresentative(List<Long> gradesId) {
        List<StudentDto> classRepresents =  new ArrayList<>();
        for (StudentDto s : getAllStudentByGrades(gradesId)) {
            User user = userService.getEntityById(s.getUser().getId());
            Set<Role> roleSet = user.getRoleSet();
            for (Role r : roleSet) {
                if (r.getId() == 7) classRepresents.add(s);
            }
        }
        return classRepresents;
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
        for (StudentDto s : getAllStudent())
            if (s.getUser().getIsActive()) activeStudents.add(s);
        return activeStudents;
    }

    @Override
    public List<StudentDto> getAllStudentByGrade(Long gradeId) {
        List<Student> studentList = new ArrayList<>();
        for (Student s : getAllStudentEntity())
            if (s.getGrade().getId().equals(gradeId) && s.getUser().getIsActive())
                studentList.add(s);
        return studentMapper.entityToDtoList(studentList);
    }

    @Override
    public List<StudentDto> getAllStudentByGrades(List<Long> gradeId) {
        List<Student> studentList = new ArrayList<>();
        for (Student s : getAllStudentEntity())
            for (Long id : gradeId)
                if (s.getGrade().getId().equals(id))
                    studentList.add(s);
        return studentMapper.entityToDtoList(studentList);
    }
}
