package jtm.activity06;

public interface Alien {

    /**
     * Weight of aliens is -1 at the birth
     */
    int BirthWeight = -1;

    /**
     * @param item If Alien is hungry (stomach is empty), eat Object. Possibly eaten
     *             items are:
     *             <p>
     *             1. Integer, 2. Humanoid, 3. Alien.
     *             <p>
     *             Gain weight of eaten item, and kill it, if possible
     */
    void eat(Object item);

    /**
     * @return content of the Alien stomach
     */
    Object vomit();

    /**
     * @return weight of the alien
     */
    int getWeight();

    /**
     * @return "I AM IMMORTAL!" because alien is immortal
     */
    String isAlive();

    /**
     * @return "I AM IMMORTAL!" because alien is immortal
     */
    String killHimself();

    /**
     * @return string in form "ImplementingType: weight [contenOfstomach]";
     */
    String toString();
}
