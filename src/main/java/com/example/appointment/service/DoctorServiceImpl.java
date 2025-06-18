package com.example.appointment.service;

import com.example.appointment.model.Doctor;
import com.example.appointment.model.User;
import com.example.appointment.repository.DoctorRepository;
import com.example.appointment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;

    @Override
    public Doctor findByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + username));

        return doctorRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found for user: " + username));
    }
}
