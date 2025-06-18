package com.example.appointment.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.appointment.model.City;

public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findByState_StateId(Long stateId);
}
