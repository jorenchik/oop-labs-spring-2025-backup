package jtm.activity14;

public class Car {

    private final int id;
    private final String model;
    private final Integer year;
    private final String color;
    private final Float price;

    public Car(int id, String model, Integer year, String color, Float price) {
        this.id    = id;
        this.model = model;
        this.year  = year;
        this.color = color;
        this.price = price;
    }

    // getters

    public Integer getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public Integer getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    public Float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Car{" +
               "id="    + id +
               ", model='" + model + '\'' +
               ", year="   + year +
               ", color='" + color + '\'' +
               ", price="  + price +
               '}';
    }
}
