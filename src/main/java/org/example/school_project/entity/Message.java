package org.example.school_project.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

import org.example.school_project.enums.MessageTheme;

@Entity
@Table(name = "messages")
@Builder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "theme", nullable = false)
    @Enumerated(EnumType.STRING)
    private MessageTheme theme;
    @Column(name = "title")
    private String title;
    @Column(name = "message", nullable = false)
    private String message;
    @Column(name = "is_read")
    private Boolean isRead = false;
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;
    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User authorMessage;

    @ManyToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "id")
    private User receiverMessage;

    @PrePersist
    private void prePersist() {
        creationDate = LocalDateTime.now();
    }
}
