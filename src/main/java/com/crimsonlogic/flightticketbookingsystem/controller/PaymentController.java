package com.crimsonlogic.flightticketbookingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crimsonlogic.flightticketbookingsystem.entity.Booking;
import com.crimsonlogic.flightticketbookingsystem.entity.Payment;
import com.crimsonlogic.flightticketbookingsystem.service.BookingService;
import com.crimsonlogic.flightticketbookingsystem.service.PaymentService;
import com.crimsonlogic.flightticketbookingsystem.service.TicketService;

@Controller
@RequestMapping("/payments")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private BookingService bookingService;

	@Autowired
	private TicketService ticketService;

	@GetMapping("/paymentform")
	public String showPaymentForm(@RequestParam("bookingId") Long bookingId, @RequestParam("flightId") Long flightId,
			@RequestParam("seatsBooked") String seatsBooked, @RequestParam("noOfPassengers") Integer noOfPassengers,
			@RequestParam("amount") Float amount, Model model) {
		// Log the parameters to ensure they are received correctly
		System.out.println("bookingId: " + bookingId);
		System.out.println("flightId: " + flightId);
		System.out.println("seatsBooked: " + seatsBooked);
		System.out.println("noOfPassengers: " + noOfPassengers);
		System.out.println("amount: " + amount);

		model.addAttribute("bookingId", bookingId);
		model.addAttribute("flightId", flightId);
		model.addAttribute("seatsBooked", seatsBooked);
		model.addAttribute("noOfPassengers", noOfPassengers);
		model.addAttribute("amount", amount);
		return "payment";
	}

	@PostMapping("/processpayment")
	public String processPayment(@RequestParam("bookingId") Long bookingId, @RequestParam("amount") float amount,
			@RequestParam("paymentMethod") String paymentMethod, Model model) {

		Payment payment = new Payment();
		payment.setAmount(amount);
		payment.setPaymentMethod(paymentMethod);

		Booking booking = bookingService.getBookingById(bookingId);
		payment.setBooking(booking);

		paymentService.processPayment(payment);

		// Redirect to a new endpoint for generating and downloading the ticket
		return "redirect:/tickets/generateticket?bookingId=" + bookingId;
	}
}
