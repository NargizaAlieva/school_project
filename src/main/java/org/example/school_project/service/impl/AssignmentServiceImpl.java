package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.AssignmentDto;
import org.example.school_project.dto.AssignmentDtoRequest;
import org.example.school_project.dto.EmployeeDto;
import org.example.school_project.entity.Assignment;
import org.example.school_project.entity.User;
import org.example.school_project.repository.AssignmentRepository;
import org.example.school_project.service.AssignmentService;
import org.example.school_project.service.EmployeeService;
import org.example.school_project.service.UserService;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.example.school_project.util.mapper.AssignmentMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AssignmentServiceImpl implements AssignmentService{
    private final AssignmentRepository assignmentRepository;
    private final AssignmentMapper assignmentMapper;
    private final UserService userService;
    private final EmployeeService employeeService;

    @Override
    public AssignmentDto createAssigment(AssignmentDtoRequest assignmentDtoRequest) {
        return assignmentMapper.entityToDto(assignmentRepository.save(assignmentMapper.dtoToEntity(assignmentDtoRequest)));
    }

//    @Override
//    public AssignmentDto createToSecreter(AssignmentDtoRequest assignmentDtoRequest) {
//        Assignment assignment = assignmentMapper.dtoToEntityForSecretary(assignmentDtoRequest);
//        User author = userService.getCurrentUser();
//        EmployeeDto employeeDto = employeeService.getByUserId(author.getId());
//        User secreter = userService.getUserWithRole("SECRETARY");
//        assignment.setReceiverOfAssignments(secreter);
//        assignment.setAuthorOfAssignments(employeeService.findByIdEntity(employeeDto.getId()));
//        return assignmentMapper.entityToDto(assignmentRepository.save(assignment));
//    }
//
//    @Override
//    public AssignmentDto createToClassRepresent(AssignmentDtoRequest assignmentDtoRequest) {
//        Assignment assignment = assignmentMapper.dtoToEntityForSecretary(assignmentDtoRequest);
//        User author = userService.getCurrentUser();
//        EmployeeDto employeeDto = employeeService.getByUserId(author.getId());
//        User secreter = userService.getUserWithRole("CLASS_REPRESENTATIVE");
//        assignment.setReceiverOfAssignments(secreter);
//        assignment.setAuthorOfAssignments(employeeService.findByIdEntity(employeeDto.getId()));
//        return assignmentMapper.entityToDto(assignmentRepository.save(assignment));
//    }

    @Override
    public AssignmentDto updateAssignment(AssignmentDtoRequest assignmentDtoRequest) {
        Assignment oldAssignment = assignmentMapper.dtoToEntity(assignmentDtoRequest);
        Assignment newAssignment = new Assignment();
        newAssignment.setId(oldAssignment.getId());
        newAssignment.setAssignment(oldAssignment.getAssignment());
        newAssignment.setIsDone(oldAssignment.getIsDone());
        newAssignment.setCreationDate(oldAssignment.getCreationDate());
        newAssignment.setAuthorOfAssignments(oldAssignment.getAuthorOfAssignments());
        newAssignment.setReceiverOfAssignments(oldAssignment.getReceiverOfAssignments());
        return assignmentMapper.entityToDto(assignmentRepository.save(newAssignment));
    }

    @Override
    public AssignmentDto markAsDone(Long id) {
        Assignment assignment = assignmentRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Assignment"));
        assignment.setIsDone(true);
        save(assignment);
        return assignmentMapper.entityToDto(assignment);
    }

    @Override
    public List<AssignmentDto> getAllAssignment() {
        return assignmentMapper.entityToDtoList(assignmentRepository.findAll());
    }

    @Override
    public List<AssignmentDto> getAllUndoneAssignment() {
        List<AssignmentDto> undoneAssignment = new ArrayList<>();
        List<AssignmentDto> allAssignment = getAllAssignment();

        for (AssignmentDto assignmentDto : allAssignment) {
            if(!assignmentDto.getIsDone()) undoneAssignment.add(assignmentDto);
        }
        return undoneAssignment;
    }

    public void save(Assignment assignment) {
        assignmentRepository.save(assignment);
    }
}
