package com.crimsonlogic.flightticketbookingsystem.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "flights")
public class Flight {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "custom-id-generator")
	@GenericGenerator(name = "custom-id-generator", strategy = "com.crimsonlogic.flightticketbookingsystem.util.IdGenerator")
	@Column(name = "flight_id")
	private Long flightId;

	@Column(name = "flight_number", length = 20, unique = true, nullable = false)
	private String flightNumber;

	@ManyToOne
	@JoinColumn(name = "departure_airport_id")
	private Airport departureAirport;

	@ManyToOne
	@JoinColumn(name = "arrival_airport_id")
	private Airport arrivalAirport;

	@Column(name = "departure_time")
	private java.sql.Timestamp departureTime;

	@Column(name = "arrival_time")
	private java.sql.Timestamp arrivalTime;

	@Column(name = "status", length = 50)
	private String status;

	@Column(name = "trip_price")
	private float tripPrice;

	@Column(name = "no_of_seats")
	private int noOfSeats = 50;

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "flight")
//	private List<Seat> seats;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "flight")
	private List<Booking> bookings;

	@Override
	public String toString() {
		return "Flight{" + "flightId=" + flightId + ", flightNumber='" + flightNumber + '\'' + ", departureTime="
				+ departureTime + ", arrivalTime=" + arrivalTime + ", status='" + status + '\'' + ", tripPrice="
				+ tripPrice + '}';
	}
}
