package com.example.appointment.repository;

import com.example.appointment.model.Appointment;
import com.example.appointment.model.Doctor;
import com.example.appointment.model.PatientRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPatient(PatientRegistration patient);
    List<Appointment> findByDoctor(Doctor doctor);

}

