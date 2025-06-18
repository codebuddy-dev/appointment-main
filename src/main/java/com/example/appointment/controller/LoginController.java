package com.example.appointment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    // Login form
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Redirect to default doctor dashboard after login
    @GetMapping("/doctor/dashboard")
    public String doctorDashboard() {
        return "doctor/dashboard";
    }
}

