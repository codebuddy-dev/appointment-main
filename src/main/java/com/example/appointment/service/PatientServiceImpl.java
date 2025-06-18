package com.example.appointment.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.appointment.model.PatientRegistration;
import com.example.appointment.model.State;
import com.example.appointment.model.City;
import com.example.appointment.repository.CityRepository;
import com.example.appointment.repository.PatientRepository;
import com.example.appointment.repository.StateRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final StateRepository stateRepository;
    private final CityRepository cityRepository;

    @Override
    public PatientRegistration savePatient(PatientRegistration patient) {
        patient.setRegistrationDate(LocalDate.now());
        return patientRepository.save(patient); // Return saved object with ID
    }

    @Override
    public List<State> getAllStates() {
        return stateRepository.findAll();
    }

    @Override
    public List<City> getCitiesByState(Long stateId) {
        return cityRepository.findAll()
                .stream()
                .filter(c -> c.getState().getStateId().equals(stateId))
                .collect(Collectors.toList());
    }
}
