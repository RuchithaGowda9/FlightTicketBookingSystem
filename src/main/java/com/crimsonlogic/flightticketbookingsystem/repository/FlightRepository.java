package com.crimsonlogic.flightticketbookingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crimsonlogic.flightticketbookingsystem.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
	List<Flight> findByArrivalAirport_AirportId(Long airportId);

	List<Flight> findByDepartureAirport_AirportId(Long airportId);

	// List<Flight> findFlightsByLocation(String location);
}