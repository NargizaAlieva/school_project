package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.StudentDtoRequest;
import org.example.school_project.entity.Review;
import org.example.school_project.repository.ReviewRepository;
import org.example.school_project.service.FileService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.*;
import java.util.List;


@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private static final String FILE_PATH_STUDENTS = "documents/data.docx";

    private final ReviewRepository reviewRepository;

    @Override
    public String saveStudentDataToDocx(StudentDtoRequest studentDtoRequest) {
        File directory = new File("documents");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        XWPFDocument document;

        // Проверьте наличие файла и откройте его, если он существует
        File file = new File(FILE_PATH_STUDENTS);
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                document = new XWPFDocument(fis);
            } catch (IOException e) {
                e.printStackTrace();
                return "Error occurred while opening existing DOCX file";
            }
        } else {
            document = new XWPFDocument();
        }

        // Добавьте новый параграф с данными
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(studentDtoRequest.toString());

        // Сохраните изменения в файл
        try (FileOutputStream out = new FileOutputStream(FILE_PATH_STUDENTS)) {
            document.write(out);
        } catch (IOException e) {
            e.printStackTrace();
            return "Error occurred while saving data to DOCX file";
        }

        return "Data saved successfully";
    }
    @Override
    public ResponseEntity<byte[]> getReviewsAsDocx() {
        List<Review> reviews = reviewRepository.findAll();
        try (XWPFDocument document = new XWPFDocument();
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            for (Review review : reviews) {
                XWPFParagraph paragraph = document.createParagraph();
                paragraph.createRun().setText("Review ID: " + review.getId());
                paragraph.createRun().addBreak();
                paragraph.createRun().setText("Author ID: " + review.getReview());
                paragraph.createRun().addBreak();
                paragraph.createRun().setText("Content: " + review.getAuthorReview());
                paragraph.createRun().addBreak();
                paragraph.createRun().setText("Student Review: " + review.getStudentReview());
                paragraph.createRun().addBreak();
                paragraph.createRun().setText("Creation Date: " + review.getCreationDate());
                paragraph.createRun().addBreak();
                paragraph.createRun().setText("Is active: " + review.getIsActive());
                paragraph.createRun().addBreak();
                paragraph.createRun().addBreak();
            }

            document.write(outputStream);
            byte[] docxBytes = outputStream.toByteArray();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.add("Content-Disposition", "attachment; filename=reviews.docx");

            return new ResponseEntity<>(docxBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
