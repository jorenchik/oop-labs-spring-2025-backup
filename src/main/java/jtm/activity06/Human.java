package jtm.activity06;

public class Human implements Humanoid {

    private boolean alive = true;

    private Integer stomachContents = null;

    private int weight;

    public Human() {
        this.weight = BirthWeight;
    }

    @Override
    public void eat(Integer food) {
        if (alive && stomachContents == null && food != null) {
            stomachContents = food;
            weight += food;
        }
    }

    @Override
    public Object vomit() {
        Integer food = stomachContents;
        if (food != null) {
            weight -= food;
        }
        stomachContents = null;
        return food;
    }

    @Override
    public String isAlive() {
        return alive ? "Alive" : "Dead";
    }

    @Override
    public String killHimself() {
        alive = false;
        return "Dead";
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        String contentString = (stomachContents != null) ? stomachContents.toString() : "";
        return String.format("%s: %d [%s]", getClass().getSimpleName(), getWeight(), contentString);
    }
}