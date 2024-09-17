package com.crimsonlogic.flightticketbookingsystem.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "passenger_details")
public class PassengerDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "custom-id-generator")
	@GenericGenerator(name = "custom-id-generator", strategy = "com.crimsonlogic.flightticketbookingsystem.util.IdGenerator")
	@Column(name = "passenger_id")
	private Long passengerId;

	@ManyToOne
	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "user_id_fk"))
	private User user;

//	@OneToMany
//	@JoinColumn(name = "flight_id", foreignKey = @ForeignKey(name = "flight_id_fk"))
//	private Flight flight;

	@Column(name = "first_name", length = 50)
	private String firstName;

	@Column(name = "last_name", length = 50)
	private String lastName;

	@Column(name = "passport_number", length = 20)
	private String passportNumber;

	@Column(name = "date_of_birth")
	private Date dateOfBirth;

	@Column(name = "gender")
	private String gender;
}
