package com.smartParking.SmartParking.fee;

public class BikeFeeStrategy implements FeeStrategy {

	@Override
	public double calculate(long hours) {
		return hours * 10;
	}

}
