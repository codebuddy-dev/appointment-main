package com.example.appointment.service;

import java.util.List;

import com.example.appointment.model.City;
import com.example.appointment.model.PatientRegistration;
import com.example.appointment.model.State;

public interface PatientService {
    PatientRegistration savePatient(PatientRegistration patient);
    List<State> getAllStates();
    List<City> getCitiesByState(Long stateId);
}
