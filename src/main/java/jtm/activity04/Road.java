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
	
    public float computeNecessaryFuelForConsumption(float consumption) {
    	return (float) (distance * (consumption / 100));
    }
	
	public String toString() {
		return String.format(
			"%s — %s, %dkm", 
			from, to, distance
		);
	}
}
