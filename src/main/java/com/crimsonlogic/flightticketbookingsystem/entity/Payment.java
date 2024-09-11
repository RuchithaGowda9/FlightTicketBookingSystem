package com.crimsonlogic.flightticketbookingsystem.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "custom-id-generator")
	@GenericGenerator(name = "custom-id-generator", strategy = "com.crimsonlogic.flightticketbookingsystem.util.IdGenerator")
	@Column(name = "payment_id")
	private Long paymentId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "amount", precision = 10, scale = 2)
	private java.math.BigDecimal amount;

	@Column(name = "payment_date")
	private Timestamp paymentDate = new Timestamp(System.currentTimeMillis());

	@Column(name = "payment_method", length = 50)
	private String paymentMethod;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "booking_id")
	private Booking booking;

}
