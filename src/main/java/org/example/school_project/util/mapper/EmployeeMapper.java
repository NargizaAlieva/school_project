package org.example.school_project.util.mapper;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.EmployeeDroRequest;
import org.example.school_project.dto.EmployeeDto;
import org.example.school_project.entity.Employee;
import org.example.school_project.entity.Subject;
import org.example.school_project.entity.User;
import org.example.school_project.service.SubjectService;
import org.example.school_project.service.UserService;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EmployeeMapper {
    private final UserMapper userMapper;
    private final UserService userService;
    private final SubjectService subjectService;
    public EmployeeDto entityToDto(Employee employee) {
        Set<Subject> subjectDtoSet = employee.getSubjectSet();

        String subjectString = null;
        for (Subject s : subjectDtoSet) {
            subjectString = s.getTitle() + ", ";
        }

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setPosition(employee.getPosition());
        employeeDto.setSalary(employee.getSalary());
        employeeDto.setUserDto(userMapper.entityToDto(employee.getUser()));
        employeeDto.setSubjectSet(subjectString);
        return employeeDto;
    }

    public List<EmployeeDto> entityToDtoList(List<Employee> employees) {
        return employees.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public Set<EmployeeDto> entityToDtoSet(Set<Employee> employees) {
        return employees.stream().map(this::entityToDto).collect(Collectors.toSet());
    }

    public Employee dtoToEntity(EmployeeDroRequest employeeDroRequest) {
        User user = userService.getEntityById(employeeDroRequest.getUserId());
        Set<Long> setOfSubjectId = employeeDroRequest.getSubjectSet();
        Set<Subject> subjectSet = new HashSet<>();

        for (Long s : setOfSubjectId) {
            subjectSet.add(subjectService.findByIdEntity(s));
        }

        Employee employee = new Employee();
        employee.setId(employeeDroRequest.getId());
        employee.setPosition(employeeDroRequest.getPosition());
        employee.setSalary(employeeDroRequest.getSalary());
        employee.setUser(user);
        employee.setSubjectSet(subjectSet);
        return employee;
    }
}
