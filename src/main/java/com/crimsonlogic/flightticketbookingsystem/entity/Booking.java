package com.crimsonlogic.flightticketbookingsystem.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "custom-id-generator")
	@GenericGenerator(name = "custom-id-generator", strategy = "com.crimsonlogic.flightticketbookingsystem.util.IdGenerator")
	@Column(name = "booking_id")
	private Long bookingId;

	@Column(name = "seat_id")
	private String seatId;

	@ManyToOne
	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "user_id_fk"))
	private User user;

	@ManyToOne
	@JoinColumn(name = "flight_id", foreignKey = @ForeignKey(name = "flight_id_fk"))
	private Flight flight;

	@Column(name = "booking_date")
	private Timestamp bookingDate = new Timestamp(System.currentTimeMillis());

	@Column(name = "no_of_passengers")
	private int noOfPassengers;

	@Column(name = "status", length = 20)
	private String status = "Booked";

	@OneToOne(mappedBy = "booking")
	private Payment payment;

	@OneToMany(mappedBy = "booking")
	@BatchSize(size = 10)
	private List<PassengerDetails> passengerDetails = new ArrayList<>();

	@Override
	public String toString() {
		return "Booking{" + "bookingId=" + bookingId + ", seatId='" + seatId + '\'' + ", user="
				+ (user != null ? user.getUserId() : "null") + ", flight="
				+ (flight != null ? flight.getFlightId() : "null") + ", bookingDate=" + bookingDate
				+ ", noOfPassengers=" + noOfPassengers + ", status='" + status + '\'' + ", payment="
				+ (payment != null ? payment.getPaymentId() : "null") + '}';
	}
}
