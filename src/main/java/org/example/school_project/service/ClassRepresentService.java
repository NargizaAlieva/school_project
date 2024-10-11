package org.example.school_project.service;

import org.example.school_project.dto.*;

import java.util.List;

public interface ClassRepresentService {
    ReviewDto createReview(ReviewDtoRequest reviewDtoRequest);
    ReviewDto updateReview(ReviewDtoRequest reviewDtoRequest);
    AssignmentDto markAsDone(Long id);

    ReviewDto deleteReview(Long id);
    ReviewDto restoreReview(Long id);

    List<AssignmentDto> getAllAssignment();
    List<AssignmentDto> getAllUndoneAssignment();

    void sendMessageForGradeStudents(MessageDtoRequest messageDtoRequest);

    DutyListDto createDutyList(DutyListDtoRequest dutyListDtoRequest);
    DutyListDto updateDutyList(DutyListDtoRequest dutyListDtoRequest);
    List<DutyListDto> getAllDutyListByGrade();
}
