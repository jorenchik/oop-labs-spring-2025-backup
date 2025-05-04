package jtm.activity04;

public class TrafficManagementSystem {
    static Transport[] transports;
    static Road[] roads;

    public static void initSystem(int roads, int transports) {
        addRoads(roads);
        addTransport(transports);
    }

    public static Transport[] getTransports() {
        return transports;
    }

    public static void addTransport(int i) {
    	transports = new Transport[i];
    }

    public static void setVehicle(Transport transport, int i) {
    	transports[i] = transport;
    }

    public static void addRoads(int i) {
    	roads = new Road[i];

    }

    public static Road[] getRoads() {
        return roads;
    }

    public static void setRoad(Road road, int i) {
    	roads[i] = road;
    }
}