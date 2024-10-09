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

    public Assignment getAssignmentByIdEntity(Long id) {
        return assignmentRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Assignment"));
    }

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
        Assignment newAssignment = getAssignmentByIdEntity(assignmentDtoRequest.getId());

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

        for (AssignmentDto assignmentDto : getAllAssignment()) {
            if(!assignmentDto.getIsDone()) undoneAssignment.add(assignmentDto);
        }
        return undoneAssignment;
    }

    @Override
    public List<AssignmentDto> getAllDoneAssignment() {
        List<AssignmentDto> doneAssignment = new ArrayList<>();

        for (AssignmentDto assignmentDto : getAllAssignment()) {
            if(assignmentDto.getIsDone()) doneAssignment.add(assignmentDto);
        }
        return doneAssignment;
    }

    @Override
    public List<AssignmentDto> getAllAssignmentFromReceiver(List<Long> receiverIds) {
        List<AssignmentDto> assignmentList = new ArrayList<>();
        for (AssignmentDto a : getAllAssignment())
            for (Long id : receiverIds)
                if (a.getReceiverId().equals(id))
                    assignmentList.add(a);
        return assignmentList;
    }

    @Override
    public List<AssignmentDto> getAllAssignmentFromReceiver(Long id) {
        List<AssignmentDto> assignmentList = new ArrayList<>();
        for (AssignmentDto a : getAllAssignment())
            if (a.getReceiverId().equals(id))
                assignmentList.add(a);
        return assignmentList;
    }

    @Override
    public List<AssignmentDto> getAllUndoneAssignmentFrom(List<Long> ids) {
        List<AssignmentDto> undoneAssignment = new ArrayList<>();

        for (AssignmentDto assignmentDto : getAllAssignmentFromReceiver(ids)) {
            if(!assignmentDto.getIsDone()) undoneAssignment.add(assignmentDto);
        }
        return undoneAssignment;
    }

    @Override
    public List<AssignmentDto> getAllDoneAssignmentFrom(List<Long> ids) {
        List<AssignmentDto> doneAssignment = new ArrayList<>();

        for (AssignmentDto assignmentDto : getAllAssignmentFromReceiver(ids)) {
            if(assignmentDto.getIsDone()) doneAssignment.add(assignmentDto);
        }
        return doneAssignment;
    }

    public void save(Assignment assignment) {
        assignmentRepository.save(assignment);
    }
}
