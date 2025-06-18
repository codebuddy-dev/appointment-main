package com.example.appointment.controller;

import com.example.appointment.model.*;
import com.example.appointment.repository.*;
import com.example.appointment.service.AppointmentService;
import lombok.RequiredArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final StateRepository stateRepository;
    private final CityRepository cityRepository;
    private final HospitalRepository hospitalRepository;
    private final DepartmentRepository departmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @GetMapping("/book")
    public String showBookingForm(Model model) {
        model.addAttribute("appointment", new Appointment());

        model.addAttribute("states", stateRepository.findAll());
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("timeSlots", List.of(
                "09:00 - 09:30 AM", "09:30 - 10:00 AM", "10:00 - 10:30 AM",
                "11:00 - 11:30 AM", "11:30 - 12:00 PM"
        ));
        return "appointment_booking";
    }

    @PostMapping("/book")
public String bookAppointment(@RequestParam("registrationId") Long registrationId,
                              @RequestParam("stateId") Long stateId,
                              @RequestParam("cityId") Long cityId,
                              @RequestParam("hospitalId") Long hospitalId,
                              @RequestParam("appointmentDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate appointmentDate,
                              @RequestParam("timeSlot") String timeSlot,
                              @RequestParam("departmentId") Long departmentId,
                              @RequestParam("doctorId") Long doctorId,
                              Model model) {

    // Validate patient
    PatientRegistration patient = patientRepository.findById(registrationId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid Registration ID"));

    // Fetch related entities
    State state = stateRepository.findById(stateId).orElseThrow();
    City city = cityRepository.findById(cityId).orElseThrow();
    Hospital hospital = hospitalRepository.findById(hospitalId).orElseThrow();
    Department department = departmentRepository.findById(departmentId).orElseThrow();
    Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

    // Create and save appointment
    Appointment appointment = new Appointment();
    appointment.setPatient(patient);
    appointment.setState(state);
    appointment.setCity(city);
    appointment.setHospital(hospital);
    appointment.setAppointmentDate(appointmentDate);
    appointment.setTimeSlot(timeSlot);
    appointment.setDepartment(department);
    appointment.setDoctor(doctor);

    Appointment savedAppointment = appointmentService.bookAppointment(appointment);

    // Pass data to view
    model.addAttribute("appointment", savedAppointment);
    return "appointment_success"; // NOT a redirect
}


    @GetMapping("/success")
    public String bookingSuccess() {
        return "appointment_success";
    }

    // Optional: populate dropdowns dynamically using AJAX in real-world apps
}
