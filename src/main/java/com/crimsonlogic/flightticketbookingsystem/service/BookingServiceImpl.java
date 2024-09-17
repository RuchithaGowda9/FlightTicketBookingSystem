package com.crimsonlogic.flightticketbookingsystem.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crimsonlogic.flightticketbookingsystem.entity.Booking;
import com.crimsonlogic.flightticketbookingsystem.entity.Flight;
import com.crimsonlogic.flightticketbookingsystem.entity.User;
import com.crimsonlogic.flightticketbookingsystem.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepository;

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
		return bookingRepository.save(booking); // Save and return the booking
	}

	public Set<String> getBookedSeats(Long flightId) {
		List<Booking> bookings = bookingRepository.findByFlight_FlightId(flightId);
		return bookings.stream().map(Booking::getSeatId).collect(Collectors.toSet());
	}

	@Override
	public List<Booking> getBookingsByFlightId(Long flightId) {
		return bookingRepository.findByFlight_FlightId(flightId);
	}

	@Override
	public Booking getBookingById(Long bookingId) {
		return bookingRepository.findById(bookingId).orElse(null);
	}

	@Override
	public List<Booking> getBookingsForFlight(Long flightId) {
		// Implementation for fetching bookings based on flight ID
		return bookingRepository.findAll().stream().filter(b -> b.getFlight().getFlightId().equals(flightId))
				.collect(Collectors.toList());
	}

	@Override
	public List<Booking> getAllBookings() {
		return bookingRepository.findAll();
	}

	@Override
	public void deleteBooking(Long id) {
		bookingRepository.deleteById(id);
	}
}
