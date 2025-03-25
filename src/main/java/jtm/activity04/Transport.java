package jtm.activity04;

import java.util.Locale;

public class Transport {
    // Do not change access modifiers to encapsulate internal properties!

    // TODO #1 make these fields final
    private final String id; // Transport registration number
    private final float consumption; // fuel consumption in litres per 100km
    private int tankSize; // tank size in litres
    private float fuelInTank; // fuel in tank

    /*- TODO #2
     * Select menu Source — Generate Constructor using Fields...
     * and create constructor which sets id, consumption, tankSize
     * values of the newly created object
     * And make fuel tank full.
     */

    public Transport(String id, float consumption, int tankSize) {
		super();
		this.id = id;
		this.consumption = consumption;
		this.tankSize = tankSize;
		this.fuelInTank = tankSize;
	}

    /*- TODO #3
     * Select menu: Source — Generate getters and Setters...
     * and generate public getters for consumption, tankSize, id, and
     * fuelInTank fields
     */

	public String getId() {
		return id;
	}

//	public void setId(String id) {
//		this.id = id;
//	}

	public float getConsumption() {
		return consumption;
	}

//	public void setConsumption(float consumption) {
//		this.consumption = consumption;
//	}

	public int getTankSize() {
		return tankSize;
	}

//	public void setTankSize(int tankSize) {
//		this.tankSize = tankSize;
//	}

	public float getFuelInTank() {
		return fuelInTank;
	}

//	public void setFuelInTank(float fuelInTank) {
//		this.fuelInTank = fuelInTank;
//	}
    
    
    /*- TODO #4
     * Select menu: Source — Generate toString()...
     * and implement this method, that returns String in form:
     * "Id:ID cons:0.0l/100km, tank:00l, fuel:00.00l"
     * where ID and numbers are actual properties of the transport
     * HINT: use String.format(Locale.US, "%.2f", float) to limit shown digits
     * to 2 decimal for fractions, and dot as a decimal delimiter.
     */
	public String toString() {
		return String.format(
		    Locale.US,
			"Id:%s cons:%.1fl/100km, tank:%dl, fuel:%.2fl", 
			id, consumption, tankSize, fuelInTank
		);
	}

    // Return transport id and type as string e.g. "AAA Transport"
    // HINT: use this.getClass().getSimpleName(); to get type of transport
    protected final String getType() {
        // TODO #5 return required value
        return String.format("%s %s", id, this.getClass().getSimpleName());
    }


	// HINT: use getType() to describe transport and road.toString() to describe
    // road
    // HINT: String.format(Locale.US, "%.2f", float) to format float number with
    // fixed mask
    public String move(Road road) {
        // TODO #6 If transport has enough fuel, decrease actual amount of fuel by
        // necessary amount and return String in form:
        // "AAA Type is moving on From–To, 180km"
        // TODO #7 If there is no enough fuel in tank, return string in form:
        // "Cannot move on From–To, 180km. Necessary
        // fuel:0.00l, fuel in tank:0.00l"
    	
    	float necessaryFuel = (float) road.getDistance() * (consumption / 100);
    	if (fuelInTank >= necessaryFuel) {
    		fuelInTank -= necessaryFuel;
			return String.format(
				"%s is moving on %s — %s, %dkm",
				getType(), road.getFrom(), road.getTo(), road.getDistance()
			);
		} else {
			return String.format(
				Locale.US,
				"Cannot move on %s — %s, %dkm. Necessary fuel:%.2fl, fuel in tank:%.2fl",
				road.getFrom(), road.getTo(), road.getDistance(), necessaryFuel, fuelInTank
			);
		}
    }
}