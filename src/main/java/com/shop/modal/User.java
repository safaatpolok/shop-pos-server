package com.shop.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.domain.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false,unique = true)

    @Email(message = "Email should be valid")
    private String email;


    @JsonIgnore
    @ManyToOne

    private  Store store;
    @ManyToOne
    @JsonIgnore
    private Branch branch;

    private String phone;
     @Column(nullable = false)
    private UserRole role;
     @Column(nullable = false)
     private String password;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLogin;


    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
