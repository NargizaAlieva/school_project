package org.example.school_project.service;

import org.example.school_project.dto.EmployeeDroRequest;
import org.example.school_project.dto.EmployeeDto;
import org.example.school_project.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee findByIdEntity(Long id);
    EmployeeDto getByUserId(Long id);
    EmployeeDto createEmployee(EmployeeDroRequest employeeDtoR);
    EmployeeDto updateEmployee(EmployeeDroRequest employeeDtoR);
    List<EmployeeDto> getAllEmployee();
    List<EmployeeDto> getAllActiveEmployee();
    void deleteEmployee(Long id);
}
