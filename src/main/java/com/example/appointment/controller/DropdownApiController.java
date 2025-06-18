package com.example.appointment.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.appointment.model.City;
import com.example.appointment.model.Doctor;
import com.example.appointment.model.Hospital;
import com.example.appointment.repository.CityRepository;
import com.example.appointment.repository.DoctorRepository;
import com.example.appointment.repository.HospitalRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DropdownApiController {

    private final CityRepository cityRepository;
    private final HospitalRepository hospitalRepository;
    private final DoctorRepository doctorRepository;

    @GetMapping("/cities/{stateId}")
    public List<City> getCitiesByState(@PathVariable Long stateId) {
        return cityRepository.findByState_StateId(stateId);
    }

    @GetMapping("/hospitals/{cityId}")
public List<Hospital> getHospitalsByCity(@PathVariable Long cityId) {
    return hospitalRepository.findByCity_CityId(cityId);
}

@GetMapping("/doctors/{departmentId}")
public List<Doctor> getDoctorsByDepartment(@PathVariable Long departmentId) {
    System.out.println("Loading doctors for department: " + departmentId);
    return doctorRepository.findByDepartment_DepartmentId(departmentId);
}
}
