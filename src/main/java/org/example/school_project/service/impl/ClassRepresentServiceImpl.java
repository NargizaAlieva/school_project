package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.*;
import org.example.school_project.entity.User;
import org.example.school_project.service.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassRepresentServiceImpl implements ClassRepresentService {
    private final AssignmentService assignmentService;
    private final ReviewService reviewService;
    private final UserService userService;
    private final MessageService messageService;
    private final StudentService studentService;
    private final DutyListService dutyListService;

    private User getCurrentUser() {
        return userService.getCurrentUser();
    }

    @Override
    public ReviewDto createReview(ReviewDtoRequest reviewDtoRequest) {
        return reviewService.createReview(reviewDtoRequest, getCurrentUser().getId());
    }

    @Override
    public ReviewDto updateReview(ReviewDtoRequest reviewDtoRequest) {
        return reviewService.updateReview(reviewDtoRequest, getCurrentUser().getId());
    }

    @Override
    public AssignmentDto markAsDone(Long id) {
        return assignmentService.markAsDone(id, getCurrentUser().getId());
    }

    @Override
    public ReviewDto deleteReview(Long id) {
        return reviewService.deleteReview(id, getCurrentUser().getId());
    }

    @Override
    public ReviewDto restoreReview(Long id) {
        return reviewService.restoreReview(id, getCurrentUser().getId());
    }

    @Override
    public List<AssignmentDto> getAllAssignment() {
        return assignmentService.getAllAssignmentFromReceiver(getCurrentUser().getId());
    }

    @Override
    public List<AssignmentDto> getAllUndoneAssignment() {
        return assignmentService.getAllUndoneAssignment(getAllAssignment());
    }

    @Override
    public void sendMessageForGradeStudents(MessageDtoRequest messageDtoRequest) {
        messageService.sendMessageForGradeStudents(
                studentService.getAllStudentByGrade(
                        studentService.getStudentByUserId(
                                getCurrentUser().getId()).getGrade().getId()), messageDtoRequest);
    }

    @Override
    public DutyListDto createDutyList(DutyListDtoRequest dutyListDtoRequest) {
        return dutyListService.createDutyList(dutyListDtoRequest);
    }

    @Override
    public DutyListDto updateDutyList(DutyListDtoRequest dutyListDtoRequest) {
        return dutyListService.updateDutyList(dutyListDtoRequest);
    }

    @Override
    public List<DutyListDto> getAllDutyListByGrade() {
        return dutyListService.getAllDutyListByGrade(studentService.getStudentByUserId(
                getCurrentUser().getId()).getGrade().getId());
    }
}
