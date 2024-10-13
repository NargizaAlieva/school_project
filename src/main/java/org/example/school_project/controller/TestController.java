package org.example.school_project.controller;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.example.school_project.entity.Review;
import org.example.school_project.repository.ReviewRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.List;

@RestController
public class TestController {

   // private static final String FILE_PATH = "src/main/resources/files/data.docx";
   private static final String FILE_PATH_REVIEW = "src/main/resources/files/review.docx";

    private final ReviewRepository reviewRepository;

    public TestController(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @GetMapping("/reviews/docx")
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

    @GetMapping("/test-docx")
    public ResponseEntity<byte[]> testDocx() {
        byte[] dummyData = "This is a test".getBytes();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.add("Content-Disposition", "attachment; filename=test.txt");

        return new ResponseEntity<>(dummyData, headers, HttpStatus.OK);
    }

}
