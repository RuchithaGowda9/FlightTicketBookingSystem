package com.crimsonlogic.flightticketbookingsystem.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crimsonlogic.flightticketbookingsystem.entity.PassengerDetails;
import com.crimsonlogic.flightticketbookingsystem.entity.User;
import com.crimsonlogic.flightticketbookingsystem.repository.PassengerDetailsRepository;

@Service
public class PassengerDetailsServiceImpl implements PassengerDetailsService {

	@Autowired
	private PassengerDetailsRepository passengerDetailsRepository;

	@Override
	public void savePassengerDetails(List<PassengerDetails> passengerDetails) {
		passengerDetailsRepository.saveAll(passengerDetails);
	}

	@Override
	public List<PassengerDetails> processPassengerDetails(Map<String, String> params, User user, int noOfPassengers)
			throws Exception {
		List<PassengerDetails> passengerDetailsList = new ArrayList<>();

		for (int i = 0; i < noOfPassengers; i++) {
			PassengerDetails passenger = new PassengerDetails();

			String baseKey = "passengerDetailsList[" + i + "].";
			passenger.setFirstName(params.get(baseKey + "firstName"));
			passenger.setLastName(params.get(baseKey + "lastName"));
			passenger.setPassportNumber(params.get(baseKey + "passportNumber"));
			passenger.setDateOfBirth(Date.valueOf(params.get(baseKey + "dateOfBirth")));
			passenger.setGender(params.get(baseKey + "gender"));

			passenger.setUser(user);

			passengerDetailsList.add(passenger);
		}

		return passengerDetailsList;
	}

	@Override
	public Optional<PassengerDetails> getPassengerDetailsById(Long id) {
		return passengerDetailsRepository.findById(id);
	}

	@Override
	public List<PassengerDetails> getAllPassengerDetails() {
		return passengerDetailsRepository.findAll();
	}

	@Override
	public void deletePassengerDetails(Long id) {
		passengerDetailsRepository.deleteById(id);
	}
}
