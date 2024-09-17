package com.crimsonlogic.flightticketbookingsystem.service;

import java.util.List;
import java.util.Optional;

import com.crimsonlogic.flightticketbookingsystem.entity.Payment;

public interface PaymentService {
	Payment processPayment(Payment payment);

	Optional<Payment> getPaymentById(Long id);

	List<Payment> getAllPayments();

	void deletePayment(Long id);
}
