package org.example.school_project.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "marks")
@Builder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mark", nullable = false)
    private Integer mark;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student studentMark;

    @ManyToOne
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    private Lesson lessonMark;
}
