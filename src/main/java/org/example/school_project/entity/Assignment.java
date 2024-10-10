package org.example.school_project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "assignments")
@Builder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "assignment", nullable = false)
    private String assignment;
    @Column(name = "is_done")
    private Boolean isDone = false;
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;
    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Employee authorOfAssignments;

    @ManyToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "id")
    private User receiverOfAssignments;

    @PrePersist
    private void prePersist() {
        creationDate = LocalDateTime.now();
    }
}
