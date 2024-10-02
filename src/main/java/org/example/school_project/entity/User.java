package org.example.school_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String first_name;
    private String last_name;
    private String middle_name;
    private String phone;
    private String email;
    private String password;
    private String role;
    private LocalDateTime creation_date;

    @PrePersist
    private void prePersist() {
        creation_date = LocalDateTime.now();
    }
}

