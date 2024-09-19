package com.crimsonlogic.flightticketbookingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crimsonlogic.flightticketbookingsystem.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
	@Query("SELECT b FROM Booking b JOIN FETCH b.passengerDetails WHERE b.flight.flightId = :flightId")
	List<Booking> findBookingsByFlightId(@Param("flightId") Long flightId);

	@Query("SELECT b FROM Booking b WHERE b.flight.flightId = :flightId AND b.status = 'Booked'")
	List<Booking> findByFlight_FlightId(@Param("flightId") Long flightId);

	@Modifying
	@Query("UPDATE Booking b SET b.status = 'Cancelled' WHERE b.bookingId = :bookingId")
	void cancelBooking(@Param("bookingId") Long bookingId);

	List<String> getBookedSeatsByFlight_FlightId(Long flightId);

	List<String> findSeatIdsByFlightFlightId(Long flightId);

	List<Booking> findByUser_UserId(Long userId);

}
