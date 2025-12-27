package com.smartParking.SmartParking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smartParking.SmartParking.entity.ParkingSpot;
import com.smartParking.SmartParking.entity.SpotType;

import jakarta.persistence.LockModeType;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("SELECT p FROM ParkingSpot p WHERE p.occupied = false AND p.spotType = :type")
	List<ParkingSpot> findAvailableSpots(@Param("type") SpotType type);
}
