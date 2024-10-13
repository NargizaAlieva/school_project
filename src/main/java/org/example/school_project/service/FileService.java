package org.example.school_project.service;

import org.example.school_project.dto.StudentDtoRequest;

public interface FileService {
    String saveStudentDataToDocx(StudentDtoRequest studentDtoRequest);
}
