package jtm.activity20;

import java.util.Objects;

public record RandomPerson(String name, int age, float weight, boolean female, char smile) {
    public RandomPerson {
        Objects.requireNonNull(name, "Name must not be null");
    }
}
