package org.example.school_project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "homeworks")
@Builder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Homework {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mark")
    private Integer mark;
    @Column(name = "teacher_review")
    private String teacherReview;
    @Column(name = "is_done")
    private Boolean isDone = false;
    @Column(name = "creation_date", nullable = false)
    private Timestamp creationDate;

    @ManyToOne
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    private Lesson lessonHW;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student studentHW;

    @PrePersist
    private void prePersist() {
        creationDate = Timestamp.valueOf(LocalDateTime.now());
    }
}
