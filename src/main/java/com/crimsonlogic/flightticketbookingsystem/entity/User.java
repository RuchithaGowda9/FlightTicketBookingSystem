package com.crimsonlogic.flightticketbookingsystem.entity;

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

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "custom-id-generator")
	@GenericGenerator(name = "custom-id-generator", strategy = "com.crimsonlogic.flightticketbookingsystem.util.IdGenerator")
	@Column(name = "user_id")
	private Long userId;

	@Column(name = "first_name", length = 50)
	private String firstName;

	@Column(name = "last_name", length = 50)
	private String lastName;

	@Column(name = "email", length = 100, unique = true, nullable = false)
	private String email;

	@Column(name = "password", length = 100, nullable = false)
	private String password;

	@Column(name = "phone_number", length = 10)
	private String phoneNumber;

	@Column(name = "is_admin")
	private Boolean isAdmin = false;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private List<Booking> bookings;

}
