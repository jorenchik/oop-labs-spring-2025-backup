package jtm.activity05;

import jtm.activity04.Road;
import jtm.activity04.Transport;

public class Amphibia extends Transport {

	private final byte sails;
	private final int wheels;

	public Amphibia(String id, float consumption, int tankSize, byte sails, int wheels) {
		super(id, consumption, tankSize);
		this.sails = sails;
		this.wheels = wheels;
	}

	public byte getSails() {
		return sails;
	}

	public int getWheels() {
		return wheels;
	}

	@Override
	public String move(Road road) {
		if (road.getClass() == WaterRoad.class) {
			return Ship.sailOnWaterRoad(this, road, getSails());
		} else if (road.getClass() == Road.class) {
			return Vehicle.driveOnRoad(this, road, wheels);
		} else {
			return String.format("Cannot move on %s", road.toString());
		}
	}
}
