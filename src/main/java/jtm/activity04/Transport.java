package jtm.activity04;

import java.util.Locale;

public class Transport {
    // Do not change access modifiers to encapsulate internal properties!

    // TODO #1 make these fields final
    private String id = null; // Transport registration number
    private float consumption = 0; // fuel consumption in litres per 100km
    private int tankSize = 0; // tank size in litres
    private float fuelInTank; // fuel in tank

    /*- TODO #2
     * Select menu Source — Generate Constructor using Fields...
     * and create constructor which sets id, consumption, tankSize
     * values of the newly created object
     * And make fuel tank full.
     */


    /*- TODO #3
     * Select menu: Source — Generate getters and Setters...
     * and generate public getters for consumption, tankSize, id, and
     * fuelInTank fields
     */

    /*- TODO #4
     * Select menu: Source — Generate toString()...
     * and implement this method, that returns String in form:
     * "Id:ID cons:0.0l/100km, tank:00l, fuel:00.00l"
     * where ID and numbers are actual properties of the transport
     * HINT: use String.format(Locale.US, "%.2f", float) to limit shown digits
     * to 2 decimal for fractions, and dot as a decimal delimiter.
     */


    // Return transport id and type as string e.g. "AAA Transport"
    // HINT: use this.getClass().getSimpleName(); to get type of transport
    protected final String getType() {
        // TODO #5 return required value
        return "";
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
        return "";
    }

}
