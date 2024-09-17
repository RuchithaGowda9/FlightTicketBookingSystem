package com.crimsonlogic.flightticketbookingsystem.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.crimsonlogic.flightticketbookingsystem.entity.PassengerDetails;
import com.crimsonlogic.flightticketbookingsystem.entity.User;
import com.crimsonlogic.flightticketbookingsystem.service.PassengerDetailsService;

@Controller
public class PassengerDetailsController {

	@Autowired
	private PassengerDetailsService passengerDetailsService;

	@GetMapping("/passengerDetails")
	public String getPassengerDetailsForm(@RequestParam("flightId") Long flightId,
			@RequestParam("noOfPassengers") int noOfPassengers, Model model) {
		List<PassengerDetails> passengerDetailsList = new ArrayList<>();
		for (int i = 0; i < noOfPassengers; i++) {
			passengerDetailsList.add(new PassengerDetails());
		}
		model.addAttribute("passengerDetailsList", passengerDetailsList);
		model.addAttribute("noOfPassengers", noOfPassengers);
		model.addAttribute("flightId", flightId);
		return "passengerDetails";
	}

	@PostMapping("/submitPassengerDetails")
	public String savePassengerDetails(@RequestParam("flightId") Long flightId,
			@RequestParam Map<String, String> allParams, HttpSession session, RedirectAttributes redirectAttributes) {
		int noOfPassengers = Integer.parseInt(allParams.get("noOfPassengers"));
		try {

			User user = (User) session.getAttribute("user");

			if (user == null) {
				redirectAttributes.addFlashAttribute("error", "User not found in session.");
				return "redirect:/error";
			}

			List<PassengerDetails> passengerDetailsList = passengerDetailsService.processPassengerDetails(allParams,
					user, noOfPassengers);
			passengerDetailsService.savePassengerDetails(passengerDetailsList);

			redirectAttributes.addFlashAttribute("flightId", flightId);
			redirectAttributes.addFlashAttribute("success", "Passenger details saved successfully.");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "Failed to save passenger details: " + e.getMessage());
		}
		return "redirect:/bookings/seatSelection?flightId=" + flightId + "&noOfPassengers=" + noOfPassengers;

	}

}
