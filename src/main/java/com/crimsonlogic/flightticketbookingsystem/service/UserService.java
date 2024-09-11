package com.crimsonlogic.flightticketbookingsystem.service;

import java.util.List;

import com.crimsonlogic.flightticketbookingsystem.entity.User;
import com.crimsonlogic.flightticketbookingsystem.exception.ResourceNotFoundException;

public interface UserService {
	public User registerUser(User user);

	public List<User> listAllUsers();

	public void updateUser(long userId, User user) throws ResourceNotFoundException;

	public User showUserById(long userId);
}
