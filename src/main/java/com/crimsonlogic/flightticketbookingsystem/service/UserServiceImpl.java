package com.crimsonlogic.flightticketbookingsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crimsonlogic.flightticketbookingsystem.entity.User;
import com.crimsonlogic.flightticketbookingsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.flightticketbookingsystem.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;

	@Override
	public User registerUser(User user) {
		return userRepo.save(user);
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
	public void updateUser(long userId, User user) throws ResourceNotFoundException {
		User existingUser = showUserById(userId);
		if (existingUser != null) {
			existingUser.setFirstName(user.getFirstName());
			existingUser.setLastName(user.getLastName());
			existingUser.setPhoneNumber(user.getPhoneNumber());
			userRepo.save(existingUser);
		} else {
			throw new ResourceNotFoundException("User with ID:" + userId + " not found");
		}
	}

}
