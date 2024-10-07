package org.example.school_project.util.mapper;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.EmployeeDto;
import org.example.school_project.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EmployeeMapper {
    private final UserMapper userMapper;
    public EmployeeDto entityToDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setPosition(employee.getPosition());
        employeeDto.setSalary(employee.getSalary());
        employeeDto.setUserDto(userMapper.entityToDto(employee.getUser()));
        employeeDto.setSubjectSet(employee.getSubjectSet());
        return employeeDto;
    }

    public List<EmployeeDto> entityToDtoList(List<Employee> employees) {
        return employees.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
