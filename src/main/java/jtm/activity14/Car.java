package jtm.activity14;

public class Car {

    // now immutable
    private final int id;
    private final String model;
    private final Integer year;
    private final String color;
    private final Float price;

    /**
     * @param id     unique identifier
     * @param model  model name
     * @param year   manufacture year
     * @param color  exterior color
     * @param price  price in USD
     */
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
