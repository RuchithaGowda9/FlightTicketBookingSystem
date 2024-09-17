package com.crimsonlogic.flightticketbookingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crimsonlogic.flightticketbookingsystem.entity.Airport;

public interface AirportRepository extends JpaRepository<Airport, Long> {
}