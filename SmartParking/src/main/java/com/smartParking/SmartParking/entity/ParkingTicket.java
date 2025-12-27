package com.smartParking.SmartParking.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class ParkingTicket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String vehicleNumber;

	@Enumerated(EnumType.STRING)
	private VehicleType vehicleType;

	private LocalDateTime entryTime;
	private LocalDateTime exitTime;
	private double fee;

	@ManyToOne
	private ParkingSpot spot;
}
