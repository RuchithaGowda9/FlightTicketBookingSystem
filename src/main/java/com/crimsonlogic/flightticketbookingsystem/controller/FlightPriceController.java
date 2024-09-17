package com.crimsonlogic.flightticketbookingsystem.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crimsonlogic.flightticketbookingsystem.entity.Airport;
import com.crimsonlogic.flightticketbookingsystem.service.AirportService;
import com.crimsonlogic.flightticketbookingsystem.service.FlightService;

@RestController
public class FlightPriceController {

	@Autowired
	private FlightService flightService;

	@Autowired
	private AirportService airportService;

	@GetMapping("/flight/calculatePrice")
	public ResponseEntity<Map<String, Float>> calculatePrice(@RequestParam Long departureAirportId,
			@RequestParam Long arrivalAirportId) {
		Optional<Airport> departureAirportOpt = airportService.getAirportById(departureAirportId);
		Optional<Airport> arrivalAirportOpt = airportService.getAirportById(arrivalAirportId);

		if (departureAirportOpt.isPresent() && arrivalAirportOpt.isPresent()) {
			float price = flightService.calculateTripPrice(departureAirportOpt.get().getAirportName(),
					arrivalAirportOpt.get().getAirportName());
			Map<String, Float> response = new HashMap<>();
			response.put("price", price);
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}
}
