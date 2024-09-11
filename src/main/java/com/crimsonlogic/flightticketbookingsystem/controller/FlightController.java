package com.crimsonlogic.flightticketbookingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crimsonlogic.flightticketbookingsystem.entity.Flight;
import com.crimsonlogic.flightticketbookingsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.flightticketbookingsystem.service.FlightService;

@Controller
@RequestMapping("/flight")
public class FlightController {

	@Autowired
	private FlightService flightService;

	@GetMapping("/add")
	public String showAddFlightForm(Model model) {
		model.addAttribute("flight", new Flight());
		return "addflight";
	}

	@PostMapping("/add")
	public String addFlight(Flight flight) {
		flightService.addFlight(flight);
		return "redirect:/flight/list?addSuccess";
	}

	@GetMapping("/update/{flightId}")
	public String showUpdateFlightForm(@PathVariable("flightId") Long flightId, Model model)
			throws ResourceNotFoundException {
		Flight flight = flightService.getFlightById(flightId);
		model.addAttribute("flight", flight);
		return "updateflight";
	}

	@PostMapping("/update/{flightId}")
	public String updateFlight(@PathVariable("flightId") Long flightId, Flight flight)
			throws ResourceNotFoundException {
		flightService.updateFlight(flightId, flight);
		return "redirect:/flight/list?updateSuccess";
	}

	@GetMapping("/list")
	public String listAllFlights(Model model, @RequestParam(required = false) String addSuccess,
			@RequestParam(required = false) String updateSuccess) {
		List<Flight> flights = flightService.getAllFlights();
		model.addAttribute("flights", flights);
		model.addAttribute("addSuccess", addSuccess);
		model.addAttribute("updateSuccess", updateSuccess);
		return "listallflights";
	}

	@GetMapping("/show/{flightId}")
	public String showFlightById(@PathVariable("flightId") Long flightId, Model model)
			throws ResourceNotFoundException {
		Flight flight = flightService.getFlightById(flightId);
		model.addAttribute("flight", flight);
		return "showflight";
	}

//	@GetMapping("/search")
//	public String searchFlights(@RequestParam("location") String location, Model model) {
//		List<Flight> flights = flightService.getFlightsByLocation(location);
//		model.addAttribute("flights", flights);
//		return "searchresults";
//	}

	@GetMapping("/byairport/{airportId}")
	public String getFlightsByAirport(@PathVariable("airportId") Long airportId,
			@RequestParam("isArrival") boolean isArrival, Model model) {
		List<Flight> flights = flightService.getFlightsByAirport(airportId, isArrival);
		model.addAttribute("flights", flights);
		return "flightsbyairport";
	}
}
