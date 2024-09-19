package com.crimsonlogic.flightticketbookingsystem.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crimsonlogic.flightticketbookingsystem.entity.Booking;
import com.crimsonlogic.flightticketbookingsystem.entity.Flight;
import com.crimsonlogic.flightticketbookingsystem.entity.PassengerDetails;
import com.crimsonlogic.flightticketbookingsystem.entity.User;
import com.crimsonlogic.flightticketbookingsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.flightticketbookingsystem.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private FlightService flightService;

	@Override
	public List<String> getBookedSeatsByFlight_FlightId(Long flightId) {
		return bookingRepository.findSeatIdsByFlightFlightId(flightId);
	}

	/*
	 * @Override public void saveBookings(List<String> seatNumbers, User user,
	 * Flight flight, int noOfPassengers) { for (String seatNumber : seatNumbers) {
	 * Booking booking = new Booking(); booking.setSeatId(seatNumber);
	 * booking.setUser(user); booking.setFlight(flight);
	 * booking.setNoOfPassengers(noOfPassengers); booking.setBookingDate(new
	 * Timestamp(System.currentTimeMillis())); bookingRepository.save(booking); } }
	 */
	@Override
	public Booking saveBookings(String seatNumbers, User user, Flight flight, int noOfPassengers) {
		Booking booking = new Booking();

		booking.setSeatId(seatNumbers);
		booking.setUser(user);
		booking.setFlight(flight);
		booking.setNoOfPassengers(noOfPassengers);
		booking.setBookingDate(new Timestamp(System.currentTimeMillis()));
		int updatedSeatCount = flight.getNoOfSeats() - noOfPassengers;
		try {
			flightService.updateFlightSeats(flight.getFlightId(), updatedSeatCount);
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}
		return bookingRepository.save(booking);
	}

	public Set<String> getBookedSeats(Long flightId) {
		List<Booking> bookings = bookingRepository.findByFlight_FlightId(flightId);
		return bookings.stream().map(Booking::getSeatId).collect(Collectors.toSet());
	}

	public List<PassengerDetails> getPassengersByFlightId(Long flightId) {
		List<Booking> bookings = bookingRepository.findBookingsByFlightId(flightId);
		List<PassengerDetails> passengers = new ArrayList<>();
		for (Booking booking : bookings) {
			passengers.addAll(booking.getPassengerDetails());
		}
		return passengers;
	}

	@Override
	public List<Booking> getBookingsByFlightId(Long flightId) {
		return bookingRepository.findByFlight_FlightId(flightId);
	}

	@Override
	public Booking getBookingById(Long bookingId) {
		return bookingRepository.findById(bookingId).orElse(null);
	}

	public List<Booking> getBookingsByUserId(Long userId) {
		return bookingRepository.findByUser_UserId(userId);
	}

	@Override
	public List<Booking> getBookingsForFlight(Long flightId) {
		return bookingRepository.findAll().stream().filter(b -> b.getFlight().getFlightId().equals(flightId))
				.collect(Collectors.toList());
	}

	@Override
	public List<Booking> getAllBookings() {
		return bookingRepository.findAll();
	}

	@Override
	@Transactional
	public void cancelBooking(Long bookingId) {
		bookingRepository.cancelBooking(bookingId);
	}

	@Override
	public void deleteBooking(Long id) {
		bookingRepository.deleteById(id);
	}
}
