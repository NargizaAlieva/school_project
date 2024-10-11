package org.example.school_project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "duty_list")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DutyList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "duty_date")
    private Date dutyDate;

    @ManyToOne()
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student studentDuty;

    @ManyToOne()
    @JoinColumn(name = "grade_id", referencedColumnName = "id")
    private Grade gradeDuty;
}
