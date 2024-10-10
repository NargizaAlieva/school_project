package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.util.exception.AlreadyExistException;
import org.springframework.stereotype.Service;

import org.example.school_project.dto.EmployeeDroRequest;
import org.example.school_project.dto.EmployeeDto;
import org.example.school_project.entity.Employee;
import org.example.school_project.entity.Grade;
import org.example.school_project.entity.User;
import org.example.school_project.repository.EmployeeRepository;
import org.example.school_project.service.EmployeeService;
import org.example.school_project.service.UserService;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.example.school_project.util.mapper.EmployeeMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final UserService userService;

    public Employee save (Employee employee){
        return employeeRepository.save(employee);
    }
    public List<Employee> getAllEmployeeEntity() {
        return employeeRepository.findAll();
    }
    public Boolean isExistByUserId(Long id) {
        for (EmployeeDto e : getAllActiveEmployee())
            if (e.getUserDto().getId().equals(id))
                return true;
        return false;
    }

    @Override
    public Employee findByIdEntity(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Employee"));
    }

    @Override
    public Employee getByUserId(Long id) {
        List<Employee> allEmployee = getAllEmployeeEntity();
        for (Employee employee : allEmployee) {
            if (employee.getUser().getId().equals(id))
                return employee;
        }
        throw new ObjectNotFoundException("Employee");
    }

    @Override
    public List<Long> getHomeClassesId() {
        Employee employee = getByUserId(userService.getCurrentUser().getId());
        List<Long> homeGradeId = new ArrayList<>();
        for (Grade g : employee.getHomeGrades())
            homeGradeId.add(g.getId());
        return homeGradeId;
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDroRequest employeeDtoR) {
        if (employeeRepository.existsById(employeeDtoR.getId()))
            throw new AlreadyExistException("Employee", "'id'");
        if (isExistByUserId(employeeDtoR.getUserId()))
            throw new AlreadyExistException("Employee", "'userId'");

        return employeeMapper.entityToDto(save(employeeMapper.dtoToEntity(employeeDtoR)));
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDroRequest employeeDtoR) {
        if (userService.getById(employeeDtoR.getUserId()) == null)
            throw new ObjectNotFoundException("User");

        Employee oldEmployee = employeeMapper.dtoToEntity(employeeDtoR);
        Employee newEmployee = findByIdEntity(employeeDtoR.getId());

        if(!oldEmployee.getUser().getId().equals(newEmployee.getUser().getId()))
            if (isExistByUserId(employeeDtoR.getUserId()))
                throw new AlreadyExistException("Employee", "'userId'");

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
        List<EmployeeDto> activeEmployeeDtoList = new ArrayList<>();

        for (EmployeeDto employeeDto : getAllEmployee()) {
            if(employeeDto.getUserDto().getIsActive()) activeEmployeeDtoList.add(employeeDto);
        }
        return activeEmployeeDtoList;
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = findByIdEntity(id);
        User user = employee.getUser();
        user.setIsActive(false);
        userService.saveUser(user);
    }
}
