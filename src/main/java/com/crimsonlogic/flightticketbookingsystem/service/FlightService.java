package com.crimsonlogic.flightticketbookingsystem.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.crimsonlogic.flightticketbookingsystem.entity.Flight;
import com.crimsonlogic.flightticketbookingsystem.exception.FlightConflictException;
import com.crimsonlogic.flightticketbookingsystem.exception.ResourceNotFoundException;

public interface FlightService {
	void addFlight(String flightNumber, Long departureAirportId, Long arrivalAirportId, String departureTime,
			String arrivalTime, String status) throws FlightConflictException;

	void saveFlight(Flight flight);

	boolean flightExists(String flightNumber);

	float calculateTripPrice(String departureAirportName, String arrivalAirportName);

	List<Flight> getAllFlights();

	List<Flight> searchFlights(Long departureAirportId, Long arrivalAirportId, Timestamp departureDate);

	Optional<Flight> getFlightByNumber(String flightNumber);

	void updateFlightSeats(Long flightId, int newSeatCount) throws ResourceNotFoundException;

	Flight getFlightById(Long flightId) throws ResourceNotFoundException;

	List<Flight> getFlightsByAirport(Long airportId, boolean isArrival);

	Flight findById(Long id);

	void updateFlight(Long id, LocalDateTime departureDateTime, LocalDateTime arrivalDateTime, String status,
			Float tripPrice) throws ResourceNotFoundException;

}
