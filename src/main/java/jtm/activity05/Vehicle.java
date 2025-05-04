package jtm.activity05;

import jtm.activity04.Road;
import jtm.activity04.Transport;

public class Vehicle extends Transport {

	protected final int wheels;

	public Vehicle(String id, float consumption, int tankSize, int wheels) {
		super(id, consumption, tankSize);
		this.wheels = wheels;
	}

	public int getWheels() {
		return wheels;
	}

	public static String driveOnRoad(Transport vehicle, Road road, int wheels) {
		float necessaryFuel = road.computeNecessaryFuelForConsumption(vehicle.getConsumption());
		if (vehicle.getFuelInTank() >= necessaryFuel) {
			vehicle.setFuelInTank(vehicle.getFuelInTank() - necessaryFuel); 
			return String.format(
				"%s %s is driving on %s with %d wheels",
				vehicle.getId(),
				vehicle.getClass().getSimpleName(),
				road.toString(),
				wheels
			);
		} else {
			return cannotMove(road, necessaryFuel, vehicle.getFuelInTank());
		}
	}

	@Override
	public String move(Road road) {
		if (road.getClass() == Road.class) {
			return driveOnRoad(this, road, wheels);
		} else {
			return String.format("Cannot drive on %s", road.toString());
		}
	}
}
