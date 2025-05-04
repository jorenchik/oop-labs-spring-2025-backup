package jtm.activity04;

import java.util.Locale;

public class Transport {

    // Do not change access modifiers to encapsulate internal properties!
    private final String id; // Transport registration number
    private final float consumption; // fuel consumption in litres per 100km

    private int tankSize; // tank size in litres
    private float fuelInTank; // fuel in tank

    public Transport(String id, float consumption, int tankSize) {
		super();
		this.id = id;
		this.consumption = consumption;
		this.tankSize = tankSize;
		this.fuelInTank = tankSize;
	}

	public String getId() {
		return id;
	}

	public float getConsumption() {
		return consumption;
	}

	public int getTankSize() {
		return tankSize;
	}

	public void setTankSize(int tankSize) {
		this.tankSize = tankSize;
	}

	public float getFuelInTank() {
		return fuelInTank;
	}

	public void setFuelInTank(float fuelInTank) {
		this.fuelInTank = fuelInTank;
	}
    
	public String toString() {
		return String.format(
		    Locale.US,
			"Id:%s cons:%.1fl/100km, tank:%dl, fuel:%.2fl", 
			id, consumption, tankSize, fuelInTank
		);
	}

    protected final String getType() {
        return String.format("%s %s", id, this.getClass().getSimpleName());
    }
    
    protected static String cannotMove(Road road, float necessaryFuel, float fuelInTank) {
		return String.format(
			Locale.US,
			"Cannot move on %s — %s, %dkm. Necessary fuel:%.2fl, fuel in tank:%.2fl",
			road.getFrom(), road.getTo(), road.getDistance(), necessaryFuel, fuelInTank
		);
    }

    protected String moving(Road road) {
		return String.format(
			"%s is moving on %s — %s, %dkm",
			getType(), road.getFrom(), road.getTo(), road.getDistance()
		);
    }

    public String move(Road road) {
    	float necessaryFuel = road.computeNecessaryFuelForConsumption(consumption);
    	if (fuelInTank >= necessaryFuel) {
    		fuelInTank -= necessaryFuel;
    		return moving(road);
		} else {
			return cannotMove(road, necessaryFuel, getFuelInTank());
		}
    }
}