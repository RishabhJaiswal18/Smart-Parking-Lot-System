package com.smartParking.SmartParking.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartParking.SmartParking.entity.ParkingSpot;
import com.smartParking.SmartParking.entity.ParkingTicket;
import com.smartParking.SmartParking.entity.SpotType;
import com.smartParking.SmartParking.entity.VehicleType;
import com.smartParking.SmartParking.fee.FeeStrategy;
import com.smartParking.SmartParking.fee.FeeStrategyFactory;
import com.smartParking.SmartParking.repository.ParkingSpotRepository;
import com.smartParking.SmartParking.repository.ParkingTicketRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ParkingService {

	@Autowired
	private ParkingSpotRepository spotRepo;

	@Autowired
	private ParkingTicketRepository ticketRepo;

	public ParkingTicket checkIn(String vehicleNumber, VehicleType vehicleType) {

		SpotType requiredSpot = getSpotType(vehicleType);

		List<ParkingSpot> spots = spotRepo.findAvailableSpots(requiredSpot);

		if (spots.isEmpty()) {
			throw new RuntimeException("Parking Full");
		}

		ParkingSpot spot = spots.get(0); // pick first available spot
		spot.setOccupied(true);
		spotRepo.save(spot);

		ParkingTicket ticket = new ParkingTicket();
		ticket.setVehicleNumber(vehicleNumber);
		ticket.setVehicleType(vehicleType);
		ticket.setEntryTime(LocalDateTime.now());
		ticket.setSpot(spot);

		return ticketRepo.save(ticket);
	}

	public double checkOut(Long ticketId) {

		ParkingTicket ticket = ticketRepo.findById(ticketId).orElseThrow(() -> new RuntimeException("Invalid Ticket"));

		ticket.setExitTime(LocalDateTime.now());

		long hours = Duration.between(ticket.getEntryTime(), ticket.getExitTime()).toHours();

		if (hours == 0)
			hours = 1;

		FeeStrategy strategy = FeeStrategyFactory.getStrategy(ticket.getVehicleType());
		double fee = strategy.calculate(hours);

		ticket.setFee(fee);

		ParkingSpot spot = ticket.getSpot();
		spot.setOccupied(false);
		spotRepo.save(spot);

		ticketRepo.save(ticket);

		return fee;
	}

	private SpotType getSpotType(VehicleType type) {
		if (type == VehicleType.MOTORCYCLE)
			return SpotType.SMALL;
		if (type == VehicleType.CAR)
			return SpotType.MEDIUM;
		return SpotType.LARGE;
	}
}
