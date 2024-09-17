package com.crimsonlogic.flightticketbookingsystem.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.crimsonlogic.flightticketbookingsystem.entity.PassengerDetails;
import com.crimsonlogic.flightticketbookingsystem.entity.User;

public interface PassengerDetailsService {
	void savePassengerDetails(List<PassengerDetails> passengerDetails);

	public List<PassengerDetails> processPassengerDetails(Map<String, String> params, User user, int noOfPassengers)
			throws Exception;

	Optional<PassengerDetails> getPassengerDetailsById(Long id);

	List<PassengerDetails> getAllPassengerDetails();

	void deletePassengerDetails(Long id);
}
