package jtm.extra04;

public class Dog extends Mammal {
    private String name;

    public void setName(String name) {
        if (name != null && name.matches("[A-Z][a-zA-Z]*")) {
            this.name = name;
        } else {
            this.name = "";
        }
    }

    public String getName() {
        return name;
    }
}
