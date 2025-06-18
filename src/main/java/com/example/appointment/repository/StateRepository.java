package com.example.appointment.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.appointment.model.State;

public interface StateRepository extends JpaRepository<State, Long> {}