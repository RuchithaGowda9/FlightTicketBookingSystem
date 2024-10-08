package com.crimsonlogic.flightticketbookingsystem.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "airports")
public class Airport implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "custom-id-generator")
	@GenericGenerator(name = "custom-id-generator", strategy = "com.crimsonlogic.flightticketbookingsystem.util.IdGenerator")
	@Column(name = "airport_id")
	private Long airportId;

	@Column(name = "airport_code", length = 10, unique = true, nullable = false)
	private String airportCode;

	@Column(name = "airport_name", length = 100)
	private String airportName;

	@Column(name = "city", length = 50)
	private String city;

	@Column(name = "country", length = 50)
	private String country;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "departureAirport")
	private List<Flight> departures;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "arrivalAirport")
	private List<Flight> arrivals;

	@Override
	public String toString() {
		return "Airport{" + "airportId=" + airportId + ", airportCode='" + airportCode + '\'' + ", airportName='"
				+ airportName + '\'' + ", city='" + city + '\'' + ", country='" + country + '\'' + '}';
	}

}
