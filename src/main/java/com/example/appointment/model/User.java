package com.example.appointment.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String username;  // could be email

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role; // e.g., "DOCTOR", "ADMIN"

    @OneToOne(mappedBy = "user")
    @JsonManagedReference
    private Doctor doctor;
}
