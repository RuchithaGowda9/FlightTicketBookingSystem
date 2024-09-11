package com.crimsonlogic.flightticketbookingsystem.service;

import java.util.List;

import com.crimsonlogic.flightticketbookingsystem.entity.Flight;
import com.crimsonlogic.flightticketbookingsystem.exception.ResourceNotFoundException;

public interface FlightService {
	void addFlight(Flight flight);

	void updateFlight(Long flightId, Flight flight) throws ResourceNotFoundException;

	List<Flight> getAllFlights();

	Flight getFlightById(Long flightId) throws ResourceNotFoundException;

	// List<Flight> getFlightsByLocation(String location);

	List<Flight> getFlightsByAirport(Long airportId, boolean isArrival);
}
