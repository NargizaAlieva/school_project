package org.example.school_project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lessons")
@Builder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "topic", nullable = false)
    private String topic;
    @Column(name = "homework")
    private String homework;
    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "schedule_id", referencedColumnName = "id")
    private Schedule schedule;

    @OneToMany(mappedBy = "lessonMark")
    private List<Mark> markList = new ArrayList<>();
    @OneToMany(mappedBy = "lessonAttendance")
    private List<Attendance> attendanceList = new ArrayList<>();
    @OneToMany(mappedBy = "lessonHW")
    private List<Homework> homeworkList = new ArrayList<>();

    @PrePersist
    private void prePersist() {
        creationDate = LocalDateTime.now();
    }
}
