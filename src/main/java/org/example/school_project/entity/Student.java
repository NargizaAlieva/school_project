package org.example.school_project.entity;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

import org.example.school_project.enums.ParentStatus;

@Entity
@Table(name = "students")
@Builder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "parent_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ParentStatus parentStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private Parent parent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "studentReview")
    private List<Review> reviewList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "studentHW")
    private List<Homework> homeworkList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "studentMark")
    private List<Mark> markList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "studentAttendance")
    private List<Attendance> attendanceList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "studentDuty")
    private List<DutyList> dutyLists;

    @ManyToOne
    @JoinColumn(name = "grade_id", referencedColumnName = "id")
    private Grade grade;
}
