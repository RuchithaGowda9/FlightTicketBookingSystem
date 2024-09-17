package com.crimsonlogic.flightticketbookingsystem.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.crimsonlogic.flightticketbookingsystem.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

	@Modifying
	@Transactional
	@Query("UPDATE Flight f SET f.noOfSeats = :noOfSeats WHERE f.flightId = :flightId")
	void updateNumberOfSeats(Long flightId, int noOfSeats);

	List<Flight> findByArrivalAirport_AirportId(Long airportId);

	List<Flight> findByDepartureAirport_AirportId(Long airportId);

	Optional<Flight> findByFlightNumber(String flightNumber);

	boolean existsByFlightNumber(String flightNumber);

	List<Flight> findByDepartureAirport_AirportIdAndArrivalAirport_AirportIdAndDepartureTimeBetween(
			Long departureAirportId, Long arrivalAirportId, Timestamp startTime, Timestamp endTime);

}