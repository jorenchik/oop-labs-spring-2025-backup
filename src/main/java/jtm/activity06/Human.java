package jtm.activity06;

public class Human implements Humanoid {

    private boolean alive = true;

    public Integer stomachContents = 0;

    public int weight;

    public Human() {
        this.weight = BirthWeight;
    }

    public void eat(Integer food) {
        if (alive && stomachContents == 0 && food != null) {
            stomachContents = food;
            weight += food;
        }
    }

    public Integer vomit() {
        Integer food = stomachContents;
        if (food != null) {
            weight -= food;
        }
        stomachContents = 0;
        return food;
    }

    public String isAlive() {
        return alive ? "Alive" : "Dead";
    }

    public String killHimself() {
        alive = false;
        return "Dead";
    }

    public int getWeight() {
        return weight;
    }

    public String toString() {
        String contentString = (stomachContents != null) ? stomachContents.toString() : "";
        return String.format("%s: %d [%s]", getClass().getSimpleName(), getWeight(), contentString);
    }

    public Object clone() throws CloneNotSupportedException {
        Human copy = new Human();
        copy.weight = this.weight;
		copy.stomachContents = stomachContents;
        return copy;
    }
}
