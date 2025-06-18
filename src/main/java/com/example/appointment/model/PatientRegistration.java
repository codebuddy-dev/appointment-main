package com.example.appointment.model;
import java.time.LocalDate;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "patient_registration")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long registrationId;

    private LocalDate registrationDate;

    private String patientName;

    private LocalDate dob;

    private String relationType;

    private String relationName;

    private String gender;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
}

