package com.smartParking.SmartParking.fee;

import com.smartParking.SmartParking.entity.VehicleType;

public class FeeStrategyFactory {

	public static FeeStrategy getStrategy(VehicleType type) {
		switch (type) {
		case MOTORCYCLE:
			return new BikeFeeStrategy();
		case CAR:
		case BUS:
			return new CarFeeStrategy();
		default:
			throw new IllegalArgumentException("Invalid vehicle type");
		}
	}
}
