package com.crimsonlogic.flightticketbookingsystem.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.crimsonlogic.flightticketbookingsystem.entity.Flight;
import com.crimsonlogic.flightticketbookingsystem.exception.ResourceNotFoundException;

public interface FlightService {
	void addFlight(String flightNumber, Long departureAirportId, Long arrivalAirportId, String departureTime,
			String arrivalTime, String status) throws Exception;

	void saveFlight(Flight flight);

	boolean flightExists(String flightNumber);

	void updateFlight(Long id, LocalDateTime departureTime, LocalDateTime arrivalTime, String status, Float tripPrice)
			throws ResourceNotFoundException;

	float calculateTripPrice(String departureAirportName, String arrivalAirportName);

	List<Flight> getAllFlights();

	List<Flight> searchFlights(Long departureAirportId, Long arrivalAirportId, Timestamp departureDate);

	Optional<Flight> getFlightByNumber(String flightNumber);

//	boolean reserveSeats(String flightNumber, List<String> seatNumbers, int numberOfPassengers);

	// boolean updateSeatAvailability(String flightNumber, String seatNumber,
	// boolean isAvailable);

	Flight getFlightById(Long flightId) throws ResourceNotFoundException;

	List<Flight> getFlightsByAirport(Long airportId, boolean isArrival);

	Flight findById(Long id);

	void updateFlightSeats(Long flightId, int noOfSeats);

//	boolean updateSeatAvailability(String flightNumber, List<String> seatNumbers, boolean isAvailable);
}
