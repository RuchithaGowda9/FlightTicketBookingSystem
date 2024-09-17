package com.crimsonlogic.flightticketbookingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crimsonlogic.flightticketbookingsystem.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);

	boolean existsByEmail(String email);

	boolean existsByPhoneNumber(String phoneNumber);

}
