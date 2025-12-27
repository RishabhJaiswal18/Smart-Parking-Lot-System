package com.smartParking.SmartParking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartParking.SmartParking.entity.ParkingTicket;

@Repository
public interface ParkingTicketRepository extends JpaRepository<ParkingTicket, Long> {

}
