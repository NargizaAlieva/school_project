package org.example.school_project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@Builder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", nullable = false, unique = true)
    private String username;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "userSet")
    @JsonBackReference
    private Set<Role> roleSet;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "authorOfAssignments")
    private List<Assignment> authorOfAssignments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "receiverOfAssignments")
    private List<Assignment> receiverOfAssignments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "authorMessage")
    private List<Message> authorOfMessages;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "receiverMessage")
    private List<Message> receiverOfMessages;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "authorReview")
    private List<Review> authorOfReviews;

    @PrePersist
    private void prePersist() {
        creationDate = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleSet.stream()
                .map(role -> new SimpleGrantedAuthority(role.getTitle())) // Convert role name to GrantedAuthority
                .collect(Collectors.toList());
    }
    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}

