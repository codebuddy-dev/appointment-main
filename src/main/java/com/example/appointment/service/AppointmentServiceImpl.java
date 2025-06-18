package com.example.appointment.service;

import com.example.appointment.model.Appointment;
import com.example.appointment.model.PatientRegistration;
import com.example.appointment.repository.AppointmentRepository;
import com.example.appointment.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;

    @Override
    public Appointment bookAppointment(Appointment appointment) {
        // validate registration
        Long regId = appointment.getPatient().getRegistrationId();
        PatientRegistration patient = patientRepository.findById(regId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid registration ID: " + regId));

        appointment.setPatient(patient);
        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getAppointmentsByRegistrationId(Long registrationId) {
        PatientRegistration patient = patientRepository.findById(registrationId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid registration ID"));
        return appointmentRepository.findByPatient(patient);
    }

    @Override
    public Appointment updateAppointmentDetails(Long appointmentId, String medications, String tests) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found: " + appointmentId));

        appointment.setPrescribedMedications(medications);
        appointment.setRecommendedTests(tests);

        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getAppointmentsByDoctor(com.example.appointment.model.Doctor doctor) {
        return appointmentRepository.findByDoctor(doctor);
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found: " + id));
    }

}
