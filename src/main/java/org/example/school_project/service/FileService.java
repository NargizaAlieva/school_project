package org.example.school_project.service;

import org.example.school_project.dto.StudentDtoRequest;
import org.springframework.http.ResponseEntity;

public interface FileService {
    String saveStudentDataToDocx(StudentDtoRequest studentDtoRequest);
    ResponseEntity<byte[]> getReviewsAsDocx();
}
