package com.crimsonlogic.flightticketbookingsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crimsonlogic.flightticketbookingsystem.entity.Airport;
import com.crimsonlogic.flightticketbookingsystem.repository.AirportRepository;

@Service
public class AirportServiceImpl implements AirportService {

	@Autowired
	private AirportRepository airportRepository;

	@Override
	public List<Airport> getAllAirports() {
		return airportRepository.findAll();
	}

	@Override
	public Optional<Airport> getAirportById(Long id) {
		return airportRepository.findById(id);
	}
}