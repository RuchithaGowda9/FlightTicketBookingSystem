package com.crimsonlogic.flightticketbookingsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.crimsonlogic.flightticketbookingsystem.entity.User;
import com.crimsonlogic.flightticketbookingsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.flightticketbookingsystem.exception.UserAlreadyRegisteredException;
import com.crimsonlogic.flightticketbookingsystem.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;

	private String hashPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}

	private boolean checkPassword(String password, String hashedPassword) {
		return BCrypt.checkpw(password, hashedPassword);
	}

	public User registerUser(User user) {
		if (userRepo.existsByEmail(user.getEmail())) {
			throw new UserAlreadyRegisteredException("A user with this email is already registered. Please login.");
		}
		if (userRepo.existsByPhoneNumber(user.getPhoneNumber())) {
			throw new UserAlreadyRegisteredException(
					"A user with this phone number is already registered. Please login.");
		}

		user.setPassword(hashPassword(user.getPassword()));
		return userRepo.save(user);
	}

	@Override
	public User authenticateUser(String email, String password) {
		User user = userRepo.findByEmail(email);
		if (user != null && checkPassword(password, user.getPassword())) {
			return user;
		}
		return null;
	}

	@Override
	public boolean isUserAdmin(User user) {
		return user != null && user.getIsAdmin();
	}

	@Override
	public List<User> listAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public User showUserById(long userId) {
		return userRepo.findById(userId).get();
	}

	@Override
	public void updateUser(long userId, User updatedUser) throws ResourceNotFoundException {
		if (!userRepo.existsById(userId)) {
			throw new ResourceNotFoundException("User not found with id: " + userId);
		}
		User existingUser = userRepo.findById(userId).get();

		existingUser.setFirstName(updatedUser.getFirstName());
		existingUser.setLastName(updatedUser.getLastName());
		existingUser.setEmail(updatedUser.getEmail());

		userRepo.save(existingUser);
	}

}
