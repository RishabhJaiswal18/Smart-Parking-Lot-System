package com.smartParking.SmartParking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smartParking.SmartParking.entity.ParkingTicket;
import com.smartParking.SmartParking.entity.VehicleType;
import com.smartParking.SmartParking.service.ParkingService;

@RestController
@RequestMapping("/parking")
public class ParkingController {

	@Autowired
	private ParkingService parkingService;

	@PostMapping("/checkin")
	public ParkingTicket checkIn(@RequestParam String vehicleNumber, @RequestParam VehicleType vehicleType) {

		return parkingService.checkIn(vehicleNumber, vehicleType);
	}

	@PostMapping("/checkout/{ticketId}")
	public String checkOut(@PathVariable Long ticketId) {
		double fee = parkingService.checkOut(ticketId);
		return "Parking Fee: ₹" + fee;
	}
}
