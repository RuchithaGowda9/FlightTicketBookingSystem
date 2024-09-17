package com.crimsonlogic.flightticketbookingsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crimsonlogic.flightticketbookingsystem.entity.Payment;
import com.crimsonlogic.flightticketbookingsystem.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public Payment processPayment(Payment payment) {
		return paymentRepository.save(payment);
	}

	@Override
	public Optional<Payment> getPaymentById(Long id) {
		return paymentRepository.findById(id);
	}

	@Override
	public List<Payment> getAllPayments() {
		return paymentRepository.findAll();
	}

	@Override
	public void deletePayment(Long id) {
		paymentRepository.deleteById(id);
	}
}
