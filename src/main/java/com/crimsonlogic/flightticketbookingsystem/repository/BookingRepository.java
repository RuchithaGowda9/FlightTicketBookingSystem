package com.crimsonlogic.flightticketbookingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crimsonlogic.flightticketbookingsystem.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
	List<Booking> findByFlight_FlightId(Long flightId);

	List<String> getBookedSeatsByFlight_FlightId(Long flightId);

	List<String> findSeatIdsByFlightFlightId(Long flightId);
}
