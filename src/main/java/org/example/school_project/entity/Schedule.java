package org.example.school_project.entity;

import jakarta.persistence.*;
import lombok.*;

import org.example.school_project.enums.DaysOfWeek;

@Entity
@Table(name = "schedules")
@Builder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "day_of_week", nullable = false)
    @Enumerated(EnumType.STRING)
    private DaysOfWeek dayOfWeek;
    @Column(name = "quarter", nullable = false)
    private Integer quarter;
    @Column(name = "due_time", nullable = false)
    private String dueTime;
    @Column(name = "year", nullable = false)
    private String year;
    @Column(name = "is_approve")
    private Boolean isApprove = false;
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subject subjectSchedule;

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Employee teacherSchedule;

    @ManyToOne
    @JoinColumn(name = "grade_id", referencedColumnName = "id")
    private Grade gradeSchedule;
}
