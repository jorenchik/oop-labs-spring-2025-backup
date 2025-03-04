package jtm.activity13;

/**
 * This is general interface for all Crocodiles
 */
public interface Crocodile {

    /**
     * Moves crocodile on the passed board eats candies and counts moves and
     * candies
     */
    void move(Board board);

    /**
     * @return number of passed moves
     */
    int getMoves();

    /**
     * @return number of eaten candies
     */
    int getCandies();

    /**
     * @return type of the crocodile
     */
    String getType();

}
