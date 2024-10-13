package org.example.school_project.service.impl;

import org.example.school_project.dto.StudentDtoRequest;
import org.example.school_project.service.FileService;
import org.springframework.stereotype.Service;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


@Service
public class FileServiceImpl implements FileService {

    private static final String FILE_PATH_STUDENTS = "documents/data.docx";

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
}
