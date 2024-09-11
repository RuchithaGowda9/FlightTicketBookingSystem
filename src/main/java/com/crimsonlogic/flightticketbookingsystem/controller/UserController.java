package com.crimsonlogic.flightticketbookingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crimsonlogic.flightticketbookingsystem.entity.User;
import com.crimsonlogic.flightticketbookingsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.flightticketbookingsystem.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	@PostMapping("/register")
	public String registerUser(User user) {
		userService.registerUser(user);
		return "redirect:/user/login?registrationSuccess=true";
	}

	@GetMapping("/login")
	public String showLoginPage(Model model) {
		return "login";
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

	@PostMapping("/updateuser/{userid}")
	public String updateUser(@PathVariable("userid") long userId, User user, Model model) {
		try {
			userService.updateUser(userId, user);
			return "redirect:/user/showuser/" + userId + "?updated";
		} catch (ResourceNotFoundException e) {
			model.addAttribute("error", e.getMessage());
			return "error";
		}
	}
}
