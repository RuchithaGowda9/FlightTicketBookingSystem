package com.crimsonlogic.flightticketbookingsystem.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking {
	@Id
	@Column(name = "booking_id")
	private Long bookingId;

	@ManyToOne
	@JoinColumn(name = "seat_id")
	private Seat seat;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "flight_id")
	private Flight flight;

	@Column(name = "booking_date")
	private Timestamp bookingDate = new Timestamp(System.currentTimeMillis());

	@Column(name = "status", length = 20)
	private String status = "Booked";

	@OneToOne(mappedBy = "booking")
	private Payment payment;

	@OneToMany(mappedBy = "booking")
	private List<PassengerDetails> passengerDetails;

}
