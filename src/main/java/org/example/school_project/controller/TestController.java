package org.example.school_project.controller;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.example.school_project.dto.StudentDtoRequest;
import org.example.school_project.entity.YourDataClass;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
public class TestController {

    private static final String FILE_PATH = "src/main/resources/files/data.docx";

    @PostMapping("/saveData")
    public String saveDataToDatabaseAndDocx(@RequestBody StudentDtoRequest studentDtoRequest) {


        XWPFDocument document;

        File file = new File(FILE_PATH);
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

        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(studentDtoRequest.toString());

        try (FileOutputStream out = new FileOutputStream(FILE_PATH)) {
            document.write(out);
        } catch (IOException e) {
            e.printStackTrace();
            return "Error occurred while saving data to DOCX file";
        }

        return "Data saved successfully";
    }

}
