package com.example.appointment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.appointment.model.PatientRegistration;

public interface PatientRepository extends JpaRepository<PatientRegistration, Long> {}