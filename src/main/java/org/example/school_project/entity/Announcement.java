package org.example.school_project.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.school_project.enums.CanSeeOnly;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "announcements")
@Builder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "forWhom", nullable = false)
    @Enumerated(EnumType.STRING)
    private CanSeeOnly forWhom;
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;
    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee authorOfAnnouncement;

    @PrePersist
    private void prePersist() {
        creationDate = LocalDateTime.now();
    }
}
