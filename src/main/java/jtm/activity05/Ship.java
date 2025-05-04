package jtm.activity05;

import jtm.activity04.Road;
import jtm.activity04.Transport;

public class Ship extends Transport {

	protected final byte sails;

	public Ship(String id, byte sails) {
		super(id, 0, 0);
		this.sails = sails;
	}

	public byte getSails() {
		return sails;
	}

	public static String sailOnWaterRoad(Transport transport, Road road,  byte sails) {
		return String.format(
			"%s %s is sailing on %s with %d sails",
			transport.getId(),
			transport.getClass().getSimpleName(),
			road.toString(),
			sails
		);
	}

	@Override
	public String move(Road road) {
		if (road instanceof WaterRoad) {
			return sailOnWaterRoad(this, road, getSails());
		} else {
			return String.format("Cannot sail on %s", road.toString());
		}
	}
}
