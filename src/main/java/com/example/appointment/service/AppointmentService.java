package com.example.appointment.service;

import com.example.appointment.model.Appointment;

import java.util.List;

public interface AppointmentService {
    Appointment bookAppointment(Appointment appointment);
    List<Appointment> getAppointmentsByRegistrationId(Long registrationId);
    Appointment updateAppointmentDetails(Long appointmentId, String medications, String tests);
    List<Appointment> getAppointmentsByDoctor(com.example.appointment.model.Doctor doctor);
Appointment getAppointmentById(Long id);
    
}
