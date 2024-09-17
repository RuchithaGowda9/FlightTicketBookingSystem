package com.crimsonlogic.flightticketbookingsystem.service;

import java.util.List;
import java.util.Set;

import com.crimsonlogic.flightticketbookingsystem.entity.Booking;
import com.crimsonlogic.flightticketbookingsystem.entity.Flight;
import com.crimsonlogic.flightticketbookingsystem.entity.User;

public interface BookingService {

//	Booking saveBookings(List<String> seatNumbers, User user, Flight flight, int noOfPassengers);

	List<Booking> getBookingsByFlightId(Long flightId);

	Set<String> getBookedSeats(Long flightId);

	List<String> getBookedSeatsByFlight_FlightId(Long flightId);

	Booking getBookingById(Long bookingId);

	List<Booking> getBookingsForFlight(Long flightId);

	List<Booking> getAllBookings();

	void deleteBooking(Long id);

	Booking saveBookings(String seatNumbers, User user, Flight flight, int noOfPassengers);

}
