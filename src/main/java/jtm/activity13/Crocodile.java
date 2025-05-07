// File: src/jtm/activity13/Crocodile.java
package jtm.activity13;

public interface Crocodile {
    /** Moves crocodile on the passed board, eats candies and counts moves and candies */
    void move(Board board);

    /** @return number of moves made */
    int getMoves();

    /** @return number of eaten candies */
    int getCandies();

    /** @return type/name of the crocodile */
    String getType();
}
