package com.crimsonlogic.flightticketbookingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crimsonlogic.flightticketbookingsystem.entity.PassengerDetails;

@Repository
public interface PassengerDetailsRepository extends JpaRepository<PassengerDetails, Long> {
}
