package com.crimsonlogic.flightticketbookingsystem.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crimsonlogic.flightticketbookingsystem.entity.Airport;
import com.crimsonlogic.flightticketbookingsystem.entity.Flight;
import com.crimsonlogic.flightticketbookingsystem.exception.FlightConflictException;
import com.crimsonlogic.flightticketbookingsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.flightticketbookingsystem.repository.FlightRepository;
import com.crimsonlogic.flightticketbookingsystem.util.DistanceCalculator;

@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private FlightService flightService;

	@Autowired
	AirportService airportService;

	@PersistenceContext
	private EntityManager entityManager;

	private static final float PRICE_PER_KM = 5f;

	@Override
	public void addFlight(String flightNumber, Long departureAirportId, Long arrivalAirportId, String departureTime,
			String arrivalTime, String status) throws FlightConflictException {
		Optional<Airport> departureAirportOpt = airportService.getAirportById(departureAirportId);
		Optional<Airport> arrivalAirportOpt = airportService.getAirportById(arrivalAirportId);
		Optional<Flight> existingFlight = flightService.getFlightByNumber(flightNumber);
		if (existingFlight.isPresent()) {
			throw new FlightConflictException("Flight number already exists");
		}
		if (!departureAirportOpt.isPresent() || !arrivalAirportOpt.isPresent()) {
			throw new FlightConflictException("One or more airports not found.");
		}

		Flight flight = new Flight();
		flight.setFlightNumber(flightNumber);
		flight.setDepartureAirport(departureAirportOpt.get());
		flight.setArrivalAirport(arrivalAirportOpt.get());
		flight.setDepartureTime(Timestamp.valueOf(LocalDateTime.parse(departureTime)));
		flight.setArrivalTime(Timestamp.valueOf(LocalDateTime.parse(arrivalTime)));
		flight.setStatus(status);
		flight.setNoOfSeats(50);

		float tripPrice = calculateTripPrice(departureAirportOpt.get().getAirportName(),
				arrivalAirportOpt.get().getAirportName());
		flight.setTripPrice(tripPrice);

		flightRepository.save(flight);
	}

	@Override
	public float calculateTripPrice(String departureAirportName, String arrivalAirportName) {
		Integer distance = DistanceCalculator.getDistance(departureAirportName, arrivalAirportName);
		if (distance == null) {
			throw new FlightConflictException("Distance not found between given airports");
		}
		return distance * PRICE_PER_KM;
	}

	@Transactional
	@Override
	public void updateFlightSeats(Long flightId, int newSeatCount) throws ResourceNotFoundException {
		Flight flight = flightRepository.findById(flightId)
				.orElseThrow(() -> new ResourceNotFoundException("Flight not found with id " + flightId));
		flight.setNoOfSeats(newSeatCount);
		flightRepository.save(flight);
	}

	@Override
	public void updateFlight(Long id, LocalDateTime departureTime, LocalDateTime arrivalTime, String status,
			Float tripPrice) throws ResourceNotFoundException {
		Flight flight;
		try {
			flight = flightRepository.findById(id).orElseThrow(() -> new Exception("Flight not found"));
			flight.setDepartureTime(Timestamp.valueOf(departureTime));
			flight.setArrivalTime(Timestamp.valueOf(arrivalTime));
			flight.setStatus(status);
			flight.setTripPrice(tripPrice);

			flightRepository.save(flight);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Flight> getAllFlights() {
		return flightRepository.findAll();
	}

	@Override
	public Optional<Flight> getFlightByNumber(String flightNumber) {
		return flightRepository.findByFlightNumber(flightNumber);
	}

	@Override
	public Flight getFlightById(Long flightId) throws ResourceNotFoundException {
		return flightRepository.findById(flightId)
				.orElseThrow(() -> new ResourceNotFoundException("Flight not found with id " + flightId));
	}

	@Override
	public List<Flight> getFlightsByAirport(Long airportId, boolean isArrival) {
		return isArrival ? flightRepository.findByArrivalAirport_AirportId(airportId)
				: flightRepository.findByDepartureAirport_AirportId(airportId);
	}

	@Override
	public void saveFlight(Flight flight) {
		if (flight.getFlightId() == null) { // New flight
			if (flightExists(flight.getFlightNumber())) {
				throw new FlightConflictException("This flight is already scheduled.");
			}
		}
		flightRepository.save(flight);
	}

	@Override
	public boolean flightExists(String flightNumber) {
		return flightRepository.existsByFlightNumber(flightNumber);
	}

	@Override
	public Flight findById(Long id) {
		return flightRepository.findById(id).orElse(null);
	}

	@Override
	public List<Flight> searchFlights(Long departureAirportId, Long arrivalAirportId, Timestamp departureDate) {
		Timestamp startTime = new Timestamp(departureDate.getTime());
		Timestamp endTime = new Timestamp(departureDate.getTime() + 86400000L); // Adding 24 hours in milliseconds

		return flightRepository.findByDepartureAirport_AirportIdAndArrivalAirport_AirportIdAndDepartureTimeBetween(
				departureAirportId, arrivalAirportId, startTime, endTime);
	}

}
