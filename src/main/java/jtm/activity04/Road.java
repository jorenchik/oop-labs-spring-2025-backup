package jtm.activity04;

public class Road {
    private String from; // Start point
    private String to; // End point
    private int distance; // distance in km
    
	public Road(String from, String to, int distance) {
		this.from = from;
		this.to = to;
		this.distance = distance;
	}

    /*- TODO #2
     * Create constructor without parameters, which sets empty
     * values or 0 to all object properties
     */

	public Road() {
		this.from = "";
		this.to = "";
		this.distance = 0;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
	
    /*- TODO #4
     * Select menu: Source — Generate toString()...
     * and implement this method, that it returns String in form:
     * "From — To, 00km",
     * where "From" is actual from point, "To" — actual to point and
     * 00 is actual length of the road
     * Note that — is not dash ("minus key" in jargon), but m-dash!
     * See more at: https://en.wikipedia.org/wiki/Dash
     */
	public String toString() {
		return String.format(
			"%s — %s, %dkm", 
			from, to, distance
		);
	}
}
