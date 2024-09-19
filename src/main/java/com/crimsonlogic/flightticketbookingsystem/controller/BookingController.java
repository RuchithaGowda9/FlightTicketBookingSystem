package com.crimsonlogic.flightticketbookingsystem.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.crimsonlogic.flightticketbookingsystem.entity.Booking;
import com.crimsonlogic.flightticketbookingsystem.entity.Flight;
import com.crimsonlogic.flightticketbookingsystem.entity.User;
import com.crimsonlogic.flightticketbookingsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.flightticketbookingsystem.service.BookingService;
import com.crimsonlogic.flightticketbookingsystem.service.FlightService;
import com.crimsonlogic.flightticketbookingsystem.service.UserService;

@Controller
@RequestMapping("/bookings")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@Autowired
	private FlightService flightService;

	@Autowired
	private UserService userService;

	@GetMapping("/seatSelection")
	public String getSeatSelectionPage(@RequestParam("flightId") Long flightId,
			@RequestParam("noOfPassengers") int noOfPassengers, Model model) {
		try {
			Flight flight = flightService.getFlightById(flightId);

			List<Booking> bookings = bookingService.getBookingsByFlightId(flightId);
			Set<String> bookedSeats = bookings.stream().map(Booking::getSeatId).collect(Collectors.toSet());

			System.out.println("Booked Seats: " + bookedSeats);

			int totalSeats = flight.getNoOfSeats();
			List<String> allSeats = new ArrayList<>();

			int seatsPerRow = 6;
			int noOfRows = (int) Math.ceil((double) totalSeats / seatsPerRow);
			for (int i = 1; i <= totalSeats; i++) {
				allSeats.add(String.format("%d", i));
			}

			model.addAttribute("flight", flight);
			model.addAttribute("noOfPassengers", noOfPassengers);
			model.addAttribute("bookedSeats", bookedSeats);
			model.addAttribute("allSeats", allSeats);
			model.addAttribute("noOfRows", noOfRows);
			model.addAttribute("seatsPerRow", seatsPerRow);
			System.out.println(flight.getTripPrice());
			model.addAttribute("seatPrice", flight.getTripPrice());
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}

		return "seatSelection";
	}

	@PostMapping("/submitSeats")
	public String submitSeats(@RequestParam("selectedSeats") String selectedSeats,
			@RequestParam("flightId") Long flightId, @RequestParam("noOfPassengers") int noOfPassengers,
			HttpSession session, RedirectAttributes redirectAttributes) {
		List<String> seatNumbers = Arrays.asList(selectedSeats.split(","));
		User user = (User) session.getAttribute("user");

		if (user == null) {
			redirectAttributes.addFlashAttribute("error", "User not found in session.");
			return "redirect:/error";
		}

		Flight flight;
		try {
			flight = flightService.getFlightById(flightId);
			Booking lastBooking = null;
			for (String seatNumber : seatNumbers) {
				lastBooking = bookingService.saveBookings(seatNumber, user, flight, noOfPassengers);
			}

			Float amount = flight.getTripPrice() * seatNumbers.size();

			return String.format(
					"redirect:/payments/paymentform?bookingId=%d&flightId=%d&seatsBooked=%s&noOfPassengers=%d&amount=%f",
					lastBooking.getBookingId(), flightId, String.join(",", seatNumbers), noOfPassengers, amount);
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("error", "An error occurred while processing your request.");
			return "redirect:/error";
		}
	}

	@GetMapping("/viewbookings")
	public String viewBookings(Model model) {
		List<Booking> bookings = bookingService.getAllBookings();
		model.addAttribute("bookings", bookings);
		return "viewBookings";
	}

	@PostMapping("/cancel/{bookingId}")
	public String cancelBooking(@PathVariable Long bookingId) {
		bookingService.cancelBooking(bookingId);
		return "redirect:/user/bookinghistory";
	}

}
