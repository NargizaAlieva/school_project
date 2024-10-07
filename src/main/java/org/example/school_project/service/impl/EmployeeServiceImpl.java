package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.EmployeeDroRequest;
import org.example.school_project.dto.EmployeeDto;
import org.example.school_project.entity.Employee;
import org.example.school_project.entity.User;
import org.example.school_project.repository.EmployeeRepository;
import org.example.school_project.service.EmployeeService;
import org.example.school_project.service.UserService;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.example.school_project.util.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final UserService userService;

    @Override
    public Employee findByIdEntity(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Schedule"));
    }

    @Override
    public EmployeeDto getByUserId(Long id) {
        List<EmployeeDto> allEmployee = getAllEmployee();
        for (EmployeeDto employee : allEmployee) {
            if (employee.getUserDto().getId().equals(id))
                return employee;
        }
        throw new ObjectNotFoundException("Employee");
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDroRequest employeeDtoR) {
        return employeeMapper.entityToDto(employeeRepository.save(employeeMapper.dtoToEntity(employeeDtoR)));
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDroRequest employeeDtoR) {
        if (employeeRepository.findById(employeeDtoR.getId()).isEmpty()) {
            throw new ObjectNotFoundException("Employee");
        }
        if (userService.getById(employeeDtoR.getUserId()) == null) {
            throw new ObjectNotFoundException("User");
        }
        Employee oldEmployee = employeeMapper.dtoToEntity(employeeDtoR);
        Employee newEmployee = new Employee();

        newEmployee.setId(oldEmployee.getId());
        newEmployee.setPosition(oldEmployee.getPosition());
        newEmployee.setSalary(oldEmployee.getSalary());
        newEmployee.setUser(oldEmployee.getUser());
        newEmployee.setSubjectSet(oldEmployee.getSubjectSet());
        return employeeMapper.entityToDto(employeeRepository.save(newEmployee));
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        return employeeMapper.entityToDtoList(employeeRepository.findAll());
    }

    @Override
    public List<EmployeeDto> getAllActiveEmployee() {
        List<EmployeeDto> employeeDtoList = getAllEmployee();
        List<EmployeeDto> activeEmployeeDtoList = new ArrayList<>();

        for (EmployeeDto employeeDto : employeeDtoList) {
            if(employeeDto.getUserDto().getIsActive()) activeEmployeeDtoList.add(employeeDto);
        }
        return activeEmployeeDtoList;
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Employee"));
        User user = employee.getUser();
        user.setIsActive(false);
        userService.updateUser(user);
    }
}
