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
	AirportService airportService;

	@PersistenceContext
	private EntityManager entityManager;

	private static final float PRICE_PER_KM = 5f;

	@Override
	public void addFlight(String flightNumber, Long departureAirportId, Long arrivalAirportId, String departureTime,
			String arrivalTime, String status) throws Exception {
		Optional<Airport> departureAirportOpt = airportService.getAirportById(departureAirportId);
		Optional<Airport> arrivalAirportOpt = airportService.getAirportById(arrivalAirportId);

		if (!departureAirportOpt.isPresent() || !arrivalAirportOpt.isPresent()) {
			throw new Exception("One or more airports not found.");
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

	@Transactional
	@Override
	public void updateFlightSeats(Long flightId, int noOfSeats) {
		Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new RuntimeException("Flight not found"));
		flight.setNoOfSeats(noOfSeats);
		entityManager.merge(flight); // This updates the Flight entity
	}

	/*
	 * @Transactional
	 * 
	 * @Override public boolean updateSeatAvailability(String flightNumber,
	 * List<String> seatNumbers, boolean isAvailable) { Optional<Flight> flightOpt =
	 * flightRepository.findByFlightNumber(flightNumber); if
	 * (!flightOpt.isPresent()) { return false; // Flight not found }
	 * 
	 * Flight flight = flightOpt.get();
	 * 
	 * for (String seatNumber : seatNumbers) { Optional<Seat> seatOpt =
	 * flight.getSeats().stream().filter(s -> s.getSeatNumber().equals(seatNumber))
	 * .findFirst(); if (!seatOpt.isPresent() || !seatOpt.get().getIsAvailable()) {
	 * return false; // Seat is not available } }
	 * 
	 * // If all seats are available, update their availability for (String
	 * seatNumber : seatNumbers) { Optional<Seat> seatOpt =
	 * flight.getSeats().stream().filter(s -> s.getSeatNumber().equals(seatNumber))
	 * .findFirst(); if (seatOpt.isPresent()) { Seat seat = seatOpt.get();
	 * seat.setIsAvailable(isAvailable); seatRepository.save(seat); // Save each
	 * updated seat } }
	 * 
	 * return true; }
	 * 
	 * @Transactional
	 * 
	 * @Override public boolean reserveSeats(String flightNumber, List<String>
	 * seatNumbers, int numberOfPassengers) { Optional<Flight> flightOpt =
	 * flightRepository.findByFlightNumber(flightNumber); if (flightOpt.isEmpty()) {
	 * return false; // Flight not found }
	 * 
	 * Flight flight = flightOpt.get();
	 * 
	 * if (seatNumbers.size() != numberOfPassengers) { return false; // Number of
	 * seats does not match the number of passengers }
	 * 
	 * // Get the list of seats for the flight List<Seat> seats = flight.getSeats();
	 * 
	 * // Map seat numbers to seats for fast lookup Map<String, Seat> seatMap =
	 * seats.stream().collect(Collectors.toMap(Seat::getSeatNumber, seat -> seat));
	 * 
	 * // Check if all requested seats are available for (String seatNumber :
	 * seatNumbers) { Seat seat = seatMap.get(seatNumber); if (seat == null ||
	 * !seat.getIsAvailable()) { return false; // Seat is not available } }
	 * 
	 * // Reserve seats List<Seat> seatsToUpdate = new ArrayList<>(); for (String
	 * seatNumber : seatNumbers) { Seat seat = seatMap.get(seatNumber); if (seat !=
	 * null) { seat.setIsAvailable(false); // Set seat to unavailable
	 * seatsToUpdate.add(seat); // Collect seats to update } }
	 * 
	 * // Update flight's available seats
	 * flight.setAvailableSeats(flight.getAvailableSeats() - numberOfPassengers);
	 * 
	 * // Save all changes seatRepository.saveAll(seatsToUpdate); // Save all
	 * updated seats in one batch flightRepository.save(flight); // Save the updated
	 * flight
	 * 
	 * return true; }
	 */

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
