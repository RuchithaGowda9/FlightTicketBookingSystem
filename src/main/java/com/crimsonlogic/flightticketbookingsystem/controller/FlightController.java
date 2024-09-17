package com.crimsonlogic.flightticketbookingsystem.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.crimsonlogic.flightticketbookingsystem.entity.Airport;
import com.crimsonlogic.flightticketbookingsystem.entity.Flight;
import com.crimsonlogic.flightticketbookingsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.flightticketbookingsystem.service.AirportService;
import com.crimsonlogic.flightticketbookingsystem.service.FlightService;

@Controller
@RequestMapping("/flight")
public class FlightController {

	@Autowired
	private FlightService flightService;

	@Autowired
	private AirportService airportService;

	private static final Logger LOG = LoggerFactory.getLogger(FlightController.class);

	@GetMapping("/add")
	public String showAddFlightForm(Model model) {
		List<Airport> airports = airportService.getAllAirports();
		model.addAttribute("airports", airports);
		return "addflight";
	}

	@PostMapping("/add")
	public String addFlight(@RequestParam String flightNumber, @RequestParam Long departureAirportId,
			@RequestParam Long arrivalAirportId, @RequestParam String departureTime, @RequestParam String arrivalTime,
			@RequestParam(required = false) String status, RedirectAttributes redirectAttributes) {

		try {
			flightService.addFlight(flightNumber, departureAirportId, arrivalAirportId, departureTime, arrivalTime,
					status);
			redirectAttributes.addFlashAttribute("success", "Flight added successfully.");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
		}

		return "redirect:/flight/add";
	}

	@GetMapping("/viewflights")
	public String viewFlights(Model model) {
		model.addAttribute("flights", flightService.getAllFlights());
		return "viewFlights";
	}

	@GetMapping("/update/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		Flight flight = flightService.findById(id);
		if (flight != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
			String formattedDepartureTime = flight.getDepartureTime().toLocalDateTime().format(formatter);
			String formattedArrivalTime = flight.getArrivalTime().toLocalDateTime().format(formatter);

			model.addAttribute("flight", flight);
			model.addAttribute("formattedDepartureTime", formattedDepartureTime);
			model.addAttribute("formattedArrivalTime", formattedArrivalTime);
			return "updateFlight";
		}
		return "redirect:/flight/viewflights";
	}

	@PostMapping("/update")
	public String updateFlight(@RequestParam("id") Long id, @RequestParam("departureTime") String departureTime,
			@RequestParam("arrivalTime") String arrivalTime, @RequestParam("status") String status,
			@RequestParam("tripPrice") Float tripPrice, RedirectAttributes redirectAttributes) {
		try {
			LocalDateTime departureDateTime = LocalDateTime.parse(departureTime);
			LocalDateTime arrivalDateTime = LocalDateTime.parse(arrivalTime);

			flightService.updateFlight(id, departureDateTime, arrivalDateTime, status, tripPrice);

			redirectAttributes.addFlashAttribute("successMessage", "Flight " + id + " updated successfully.");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Error updating flight: " + e.getMessage());
			e.printStackTrace();
		}
		return "redirect:/flight/viewflights";
	}

	@PostMapping("/searchflights")
	public String searchFlights(@RequestParam("from") Long departureAirportId,
			@RequestParam("to") Long arrivalAirportId, @RequestParam("departureDate") String departureDateStr,
			@RequestParam("passengers") int noOfPassengers, Model model) {
		model.addAttribute("departureAirportId", departureAirportId);
		model.addAttribute("arrivalAirportId", arrivalAirportId);
		model.addAttribute("departureDate", departureDateStr);
		model.addAttribute("noOfPassengers", noOfPassengers);
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date = sdf.parse(departureDateStr);
			Timestamp departureDate = new Timestamp(date.getTime());

			List<Flight> flights = flightService.searchFlights(departureAirportId, arrivalAirportId, departureDate);
			model.addAttribute("flights", flights);

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

		return "flightResults";
	}

	@GetMapping("/show/{flightId}")
	public String showFlightById(@PathVariable("flightId") Long flightId, Model model)
			throws ResourceNotFoundException {
		Flight flight = flightService.getFlightById(flightId);
		model.addAttribute("flight", flight);
		return "showflight";
	}

	@GetMapping("/byairport/{airportId}")
	public String getFlightsByAirport(@PathVariable("airportId") Long airportId,
			@RequestParam("isArrival") boolean isArrival, Model model) {
		List<Flight> flights = flightService.getFlightsByAirport(airportId, isArrival);
		model.addAttribute("flights", flights);
		return "flightsbyairport";
	}
}
