package com.example.appointment.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.appointment.model.City;
import com.example.appointment.model.PatientRegistration;
import com.example.appointment.service.PatientService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("patient", new PatientRegistration());
        model.addAttribute("states", patientService.getAllStates());
        return "patient_registration";
    }

    @PostMapping("/register")
    public String registerPatient(@ModelAttribute PatientRegistration patient) {
        PatientRegistration savedPatient = patientService.savePatient(patient);
        return "redirect:/register?success=true&registrationId=" + savedPatient.getRegistrationId();
    }

    @ResponseBody
    @GetMapping("/cities")
    public List<City> getCities(@RequestParam Long stateId) {
        return patientService.getCitiesByState(stateId);
    }
}
