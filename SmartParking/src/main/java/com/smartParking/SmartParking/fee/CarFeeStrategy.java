package com.smartParking.SmartParking.fee;

public class CarFeeStrategy implements FeeStrategy {

	@Override
	public double calculate(long hours) {
		return hours * 20;
	}

}
