package jtm.extra02;

public class LetsRide {
    int busStopCount;       // Number of bus stops
    int passengersAtStart;  // Passengers already in bus
    int passengersCount;    // Total after route
    int seatsCount;         // Total seat capacity

    public LetsRide(int busStopCount, int passengersInStop, int seatsCount) {
        this.busStopCount = busStopCount;
        this.passengersAtStart = passengersInStop;
        this.seatsCount = seatsCount;
        this.passengersCount = passengersInStop; // Initial passengers set
    }

    public int passengersAtRouteEnd() {
        // Add new passengers stop-by-stop
        for (int stop = 1; stop <= busStopCount; stop++) {
            if (passengersCount + stop <= seatsCount) {
                passengersCount += stop;
            } else {
                // Only add as many as fit
                int availableSeats = seatsCount - passengersCount;
                passengersCount += availableSeats;
                break; // Bus full, exit early
            }
        }
        return passengersCount;
    }

    public int freeSeats() {
        return seatsCount - passengersCount;
    }

    public boolean isFull() {
        return passengersCount >= seatsCount;
    }

    // Getters/Setters already provided and unchanged
    public int getBusStopCount() {
        return busStopCount;
    }

    public void setBusStopCount(int busStopCount) {
        this.busStopCount = busStopCount;
    }

    public int getPassengersAtStart() {
        return passengersAtStart;
    }

    public void setPassengersAtStart(int passengersAtStart) {
        this.passengersAtStart = passengersAtStart;
    }

    public int getPassengersCount() {
        return passengersCount;
    }

    public void setPassengersCount(int passengersCount) {
        this.passengersCount = passengersCount;
    }

    public int getSeatsCount() {
        return seatsCount;
    }

    public void setSeatsCount(int seatsCount) {
        this.seatsCount = seatsCount;
    }
}
