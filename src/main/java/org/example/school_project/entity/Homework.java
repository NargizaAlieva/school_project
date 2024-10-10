package org.example.school_project.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private Boolean isChecked = false;
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;
    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    private Lesson lessonHW;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student studentHW;

    @PrePersist
    private void prePersist() {
        creationDate = LocalDateTime.now();
    }
}
