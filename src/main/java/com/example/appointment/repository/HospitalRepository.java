package com.example.appointment.repository;

import com.example.appointment.model.Hospital;
import com.example.appointment.model.City;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    List<Hospital> findByCity(City city);

    List<Hospital> findByCity_CityId(Long cityId);

}