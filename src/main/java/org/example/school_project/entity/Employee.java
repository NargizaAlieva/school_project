package org.example.school_project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "employees")
@Builder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "position")
    private String position;
    @Column(name = "salary")
    private Integer salary;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "authorOfAnnouncement")
    private List<Announcement> authOfAnnouncements;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "authorOfCharter")
    private List<Charter> authOfCharters;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "classTeacher")
    private List<Grade> homeGrades;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacherSchedule")
    private List<Schedule> scheduleList;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "teachersSet")
    private Set<Subject> subjectSet;
}
