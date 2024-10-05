package org.example.school_project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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
    private Timestamp creationDate;

    @ManyToOne
    @JoinColumn(name = "schedule_id", referencedColumnName = "id")
    private Schedule schedule;

//    @OneToMany(mappedBy = "lessonMark")
//    private List<Mark> markList;
//    @OneToMany(mappedBy = "lessonAttendance")
//    private List<Attendance> attendanceList;
//    @OneToMany(mappedBy = "lessonHW")
//    private List<Homework> homeworkList;

    @PrePersist
    private void prePersist() {
        creationDate = Timestamp.valueOf(LocalDateTime.now());
    }
}
