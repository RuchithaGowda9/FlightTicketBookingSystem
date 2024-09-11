package com.crimsonlogic.flightticketbookingsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "seats")
public class Seat {
	@Id
	@Column(name = "seat_id")
	private Long seatId;

	@ManyToOne
	@JoinColumn(name = "flight_id")
	private Flight flight;

	@Column(name = "seat_number", length = 10)
	private String seatNumber;

	@Column(name = "class", length = 20)
	private String seatClass;

	@Column(name = "is_available")
	private Boolean isAvailable = true;

}
