package com.example.appointment.controller;

import com.example.appointment.model.Appointment;
import com.example.appointment.model.Doctor;
import com.example.appointment.service.AppointmentService;
import com.example.appointment.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/doctor")
public class DoctorAppointmentController {

    private final AppointmentService appointmentService;
    private final DoctorService doctorService;

    // View all appointments assigned to the logged-in doctor
    @GetMapping("/appointments")
    public String viewAppointments(Authentication authentication, Model model) {
        String username = authentication.getName();
        Doctor doctor = doctorService.findByUsername(username);
        List<Appointment> appointments = appointmentService.getAppointmentsByDoctor(doctor);
        model.addAttribute("appointments", appointments);
        return "doctor/appointments"; // Thymeleaf template
    }

    // Show form to edit a specific appointment
    @GetMapping("/appointments/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        model.addAttribute("appointment", appointment);
        return "doctor/edit_appointment"; // Thymeleaf template
    }

    // Submit updated appointment details (medications and tests)
    @PostMapping("/appointments/{id}/edit")
    public String updateAppointment(
            @PathVariable Long id,
            @RequestParam String prescribedMedications,
            @RequestParam String recommendedTests) {

        appointmentService.updateAppointmentDetails(id, prescribedMedications, recommendedTests);
        return "redirect:/doctor/appointments";
    }
}
