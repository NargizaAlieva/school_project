package org.example.school_project.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reviews")
@Builder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "review", nullable = false)
    private String review;
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;
    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User authorReview;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student studentReview;

    @PrePersist
    private void prePersist() {
        creationDate = LocalDateTime.now();
    }
}
