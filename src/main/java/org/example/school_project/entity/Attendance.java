package org.example.school_project.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "attendances")
@Builder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "attended", nullable = false)
    private Boolean isAttended = false;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student studentAttendance;

    @ManyToOne
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    private Lesson lessonAttendance;
}
