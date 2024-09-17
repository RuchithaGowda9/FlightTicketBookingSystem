package com.crimsonlogic.flightticketbookingsystem.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crimsonlogic.flightticketbookingsystem.entity.Airport;
import com.crimsonlogic.flightticketbookingsystem.entity.User;
import com.crimsonlogic.flightticketbookingsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.flightticketbookingsystem.exception.UserAlreadyRegisteredException;
import com.crimsonlogic.flightticketbookingsystem.service.AirportService;
import com.crimsonlogic.flightticketbookingsystem.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private AirportService airportService;

	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@GetMapping("/home")
	public String showHomePage() {
		return "home";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	@PostMapping("/register")
	public String registerUser(@ModelAttribute("user") User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "register";
		}
		try {
			userService.registerUser(user);
		} catch (UserAlreadyRegisteredException ex) {
			model.addAttribute("error", ex.getMessage());
			return "register";
		}
		return "redirect:/user/login?registrationSuccess=true";
	}

	@GetMapping("/login")
	public String showLoginPage(Model model) {
		return "login";
	}

	@PostMapping("/login")
	public String loginUser(@RequestParam("username") String email, @RequestParam("password") String password,
			Model model, HttpSession session) {
		User user = userService.authenticateUser(email, password);
		List<Airport> airports = airportService.getAllAirports();
		if (user != null) {
			session.setAttribute("user", user);
			session.setAttribute("airports", airports);
			if (userService.isUserAdmin(user)) {
				return "adminDashboard";
			} else {
				return "userDashboard";
			}
		} else {
			model.addAttribute("error", "Invalid username or password");
			return "login";
		}
	}

	@GetMapping("/admindashboard")
	public String showAdminDashboard(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/user/login";
		}
		model.addAttribute("user", user);
		return "adminDashboard";
	}

	@GetMapping("/userdashboard")
	public String showUserDashboard(HttpSession session, Model model) {
		LOG.debug("in user dashboard");
		User user = (User) session.getAttribute("user");
		List<Airport> airports = (List<Airport>) session.getAttribute("airports");
		if (user == null) {
			return "redirect:/user/login";
		}
		// List<Airport> airports = airportService.getAllAirports();
		model.addAttribute("airports", airports);
		model.addAttribute("user", user);
		return "userDashboard";
	}

	@GetMapping("/listallusers")
	public String listAllUsers(Model model) {
		model.addAttribute("users", userService.listAllUsers());
		return "listallusers";
	}

	@GetMapping("/showuser/{userid}")
	public String showUserById(@PathVariable("userid") long userId, Model model) {
		model.addAttribute("user", userService.showUserById(userId));
		return "showuser";
	}

	@GetMapping("/edituser/{userid}")
	public String showEditUserForm(@PathVariable("userid") long userId, Model model) {
		User user = userService.showUserById(userId);
		if (user == null) {
			return "error";
		}
		model.addAttribute("user", user);
		return "editUser";
	}

	@PostMapping("/updateuser/{userid}")
	public String updateUser(@PathVariable("userid") long userId, User updatedUser, Model model) {
		try {
			User existingUser = userService.showUserById(userId);
			if (existingUser == null) {
				throw new ResourceNotFoundException("User not found with id: " + userId);
			}

			updatedUser.setPassword(existingUser.getPassword());
			existingUser.setFirstName(updatedUser.getFirstName());
			existingUser.setLastName(updatedUser.getLastName());
			existingUser.setEmail(updatedUser.getEmail());

			userService.updateUser(userId, existingUser);
			return "redirect:/user/edituser/" + userId + "?updated";
		} catch (ResourceNotFoundException e) {
			model.addAttribute("error", e.getMessage());
			return "error";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/user/home";
	}
}
