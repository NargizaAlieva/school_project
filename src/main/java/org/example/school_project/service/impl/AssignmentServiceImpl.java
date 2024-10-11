package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.AssignmentDto;
import org.example.school_project.dto.AssignmentDtoRequest;
import org.example.school_project.entity.Assignment;
import org.example.school_project.repository.AssignmentRepository;
import org.example.school_project.service.AssignmentService;
import org.example.school_project.util.exception.AlreadyExistException;
import org.example.school_project.util.exception.DontHaveAccessException;
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

    public Assignment getAssignmentByIdEntity(Long id) {
        return assignmentRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Assignment"));
    }

    public Assignment save(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }

    @Override
    public AssignmentDto createAssigment(AssignmentDtoRequest assignmentDtoRequest, Long authorId) {
        if(assignmentRepository.existsById(assignmentDtoRequest.getId()))
            throw new AlreadyExistException("Assignment", "id");
        assignmentDtoRequest.setAuthorId(authorId);
        return assignmentMapper.entityToDto(save(assignmentMapper.dtoToEntity(assignmentDtoRequest)));
    }

    @Override
    public AssignmentDto updateAssignment(AssignmentDtoRequest assignmentDtoRequest, Long authorId) {
        assignmentDtoRequest.setAuthorId(authorId);
        Assignment oldAssignment = assignmentMapper.dtoToEntity(assignmentDtoRequest);
        Assignment newAssignment = getAssignmentByIdEntity(assignmentDtoRequest.getId());

        if (!oldAssignment.getAuthorOfAssignments().getId().equals(newAssignment.getAuthorOfAssignments().getId()))
            throw new DontHaveAccessException();

        newAssignment.setAssignment(oldAssignment.getAssignment());
        newAssignment.setIsDone(oldAssignment.getIsDone());
        newAssignment.setCreationDate(oldAssignment.getCreationDate());
        newAssignment.setAuthorOfAssignments(oldAssignment.getAuthorOfAssignments());
        newAssignment.setReceiverOfAssignments(oldAssignment.getReceiverOfAssignments());
        return assignmentMapper.entityToDto(save(newAssignment));
    }

    @Override
    public AssignmentDto deleteAssignment(Long id, Long authorId) {
        Assignment assignment = getAssignmentByIdEntity(id);
        if (!assignment.getAuthorOfAssignments().getId().equals(authorId))
                throw new DontHaveAccessException();
        assignment.setIsActive(false);
        return assignmentMapper.entityToDto(save(assignment));
    }

    @Override
    public AssignmentDto restoreAssignment(Long id, Long authorId) {
        Assignment assignment = getAssignmentByIdEntity(id);
        if (!assignment.getAuthorOfAssignments().getId().equals(authorId))
            throw new DontHaveAccessException();
        assignment.setIsActive(true);
        return assignmentMapper.entityToDto(save(assignment));
    }

    @Override
    public AssignmentDto markAsDone(Long id, Long receiverId) {
        Assignment assignment = getAssignmentByIdEntity(id);
        if (!assignment.getReceiverOfAssignments().getId().equals(receiverId))
            throw new DontHaveAccessException();
        assignment.setIsDone(true);
        save(assignment);
        return assignmentMapper.entityToDto(assignment);
    }

    @Override
    public List<AssignmentDto> getAllAssignment() {
        return assignmentMapper.entityToDtoList(assignmentRepository.findAll());
    }

    @Override
    public List<AssignmentDto> getAllAssignmentByAuthor(Long authorId) {
        List<AssignmentDto> assignmentDtoList = new ArrayList<>();
        for (AssignmentDto a : getAllAssignment())
            if (a.getAuthorId().equals(authorId))
                assignmentDtoList.add(a);
        return assignmentDtoList;
    }

    @Override
    public List<AssignmentDto> getAllUndoneAssignment(List<AssignmentDto> assignmentDtoList) {
        List<AssignmentDto> undoneAssignment = new ArrayList<>();

        for (AssignmentDto assignmentDto : assignmentDtoList) {
            if(!assignmentDto.getIsDone()) undoneAssignment.add(assignmentDto);
        }
        return undoneAssignment;
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
    public List<AssignmentDto> getAllDoneAssignment(List<AssignmentDto> assignmentDtoList) {
        List<AssignmentDto> doneAssignment = new ArrayList<>();

        for (AssignmentDto assignmentDto : getAllAssignment()) {
            if(assignmentDto.getIsDone()) doneAssignment.add(assignmentDto);
        }
        return doneAssignment;
    }

    @Override
    public List<AssignmentDto> getAllDoneAssignmentFrom(List<Long> ids) {
        List<AssignmentDto> doneAssignment = new ArrayList<>();

        for (AssignmentDto assignmentDto : getAllAssignmentFromReceiver(ids)) {
            if(assignmentDto.getIsDone()) doneAssignment.add(assignmentDto);
        }
        return doneAssignment;
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
    public List<AssignmentDto> getAllAssignmentFromReceiver(List<Long> receiverIds) {
        List<AssignmentDto> assignmentList = new ArrayList<>();
        for (AssignmentDto a : getAllAssignment())
            for (Long id : receiverIds)
                if (a.getReceiverId().equals(id))
                    assignmentList.add(a);
        return assignmentList;
    }
}
