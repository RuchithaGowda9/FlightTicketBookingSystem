package com.crimsonlogic.flightticketbookingsystem.service;

import java.util.List;
import java.util.Optional;

import com.crimsonlogic.flightticketbookingsystem.entity.Airport;

public interface AirportService {
	List<Airport> getAllAirports();

	Optional<Airport> getAirportById(Long id);
}