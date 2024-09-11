package com.crimsonlogic.flightticketbookingsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crimsonlogic.flightticketbookingsystem.entity.Flight;
import com.crimsonlogic.flightticketbookingsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.flightticketbookingsystem.repository.FlightRepository;

@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	private FlightRepository flightRepository;

	@Override
	public void addFlight(Flight flight) {
		flightRepository.save(flight);
	}

	@Override
	public void updateFlight(Long flightId, Flight flight) throws ResourceNotFoundException {
		if (flightRepository.existsById(flightId)) {
			flight.setFlightId(flightId);
			flightRepository.save(flight);
		} else {
			throw new ResourceNotFoundException("Flight not found with id " + flightId);
		}
	}

	@Override
	public List<Flight> getAllFlights() {
		return flightRepository.findAll();
	}

	@Override
	public Flight getFlightById(Long flightId) throws ResourceNotFoundException {
		return flightRepository.findById(flightId)
				.orElseThrow(() -> new ResourceNotFoundException("Flight not found with id " + flightId));
	}

//	@Override
//	public List<Flight> getFlightsByLocation(String location) {
//		return flightRepository.findFlightsByLocation(location);
//	}

	@Override
	public List<Flight> getFlightsByAirport(Long airportId, boolean isArrival) {
		return isArrival ? flightRepository.findByArrivalAirport_AirportId(airportId)
				: flightRepository.findByDepartureAirport_AirportId(airportId);
	}
}
