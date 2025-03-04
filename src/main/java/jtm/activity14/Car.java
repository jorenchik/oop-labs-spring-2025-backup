package jtm.activity14;

public class Car {

    // TODO, when constructor is implemented, make these fields final
    private int id = 0;
    private String model = null;
    private int year = 0;
    private String color = null;
    private float price = 0;

    /**
     * TODO Create constructor which takes following parameters
     *
     * @param id
     * @param model
     * @param year
     * @param color
     * @param price
     */
    public Car(int id, String model, Integer year, String color, Float price) {
        this.id = id;
        this.model = model;
        this.color = color;
        this.year = year;
        this.price = price;
    }

    // TODO generate public getters of all object properties

    /**
     * TODO generate toString() method which shows all parameter names and values
     *
     * @return — string
     */
}
