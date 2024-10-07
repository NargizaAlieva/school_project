package org.example.school_project.service;

import org.example.school_project.dto.EmployeeDto;
import org.example.school_project.entity.Charter;
import org.example.school_project.entity.Employee;
import org.example.school_project.entity.Schedule;
import org.example.school_project.entity.Subject;

import java.util.List;

public interface PrincipleService {
    // methods connected with schedule
    Schedule getScheduleById(Long id);
    Schedule approveSchedule(Long id);
    List<Schedule> getAllSchedule();
    List<Schedule> getAllUnApprovedSchedule();

    // methods connected with employee
    EmployeeDto hireEmployee(Employee employee);
    EmployeeDto updateEmployee(Employee employee);
    List<EmployeeDto> getAllEmployee();
    void fireEmployee(Long id);

    // methods connected with subject
    Subject addSubject(Subject subject);
    Subject updateSubject(Subject subject);
    void deleteSubject(Long id);

    // methods connected with charter
    Charter createCharter(Charter charter);

}
